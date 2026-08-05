package productos.crud.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import productos.crud.db.Conn;
import productos.crud.domain.Producto;
import productos.crud.repository.ProductRepository;
import productos.crud.repository.ProductRepositoryImpl;
import productos.crud.service.ProductoService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static productos.crud.utils.Alertas.*;
import static productos.crud.utils.HandleThrow.checkAndThrow;
import static productos.crud.utils.validators.emptyString;

public class ProductoController {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, Integer> colId;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoPrecio;
    @FXML
    private TextField campoStock;
    @FXML
    private TextField campoBusqueda;

    private ProductoService repo = new ProductoService(new ProductRepositoryImpl(Conn.getConnection()));
    private final ObservableList<Producto> lista = FXCollections.observableArrayList();


    public ProductoController() throws SQLException {

    }

    @FXML
    public void initialize() throws SQLException {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("price"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tablaProductos.setItems(lista);
        listarProductos();
    }

    @FXML
    private void onListarClick() {
        listarProductos();
    }

    private void listarProductos() {
        this.lista.clear();
        try {
            List<Producto> list = repo.findAll();
            if (list.isEmpty())
                throw new RuntimeException("La lista no tiene productos");
            lista.addAll(list);
        } catch (SQLException e) {
            mostrarError("Error con la base de datos", e.getMessage());
        } catch (RuntimeException e) {
            mostrarInfo("lista vacia", e.getMessage());
        }

    }

    @FXML
    private void onRegistrarClick() {
        try {
            String nombre = campoNombre.getText();
            BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(campoPrecio.getText()));
            Integer stock = Integer.valueOf(campoStock.getText());
            Producto nuevo = new Producto(nombre.toLowerCase(), precio, stock);
            Optional<Producto> encontrado = repo.findByName(nuevo.getName());
            if (encontrado.isPresent())
                throw new RuntimeException("Ya existe un producto con el nombre: " + encontrado.get().getName());
            if (repo.save(nuevo)) {
                mostrarInfo("exito", "usuario ingresado con exito");
            } else {
                mostrarAdvertencia("no ingresado", "el usuario no pudo ser ingresado");
            }
        } catch (SQLException e) {
            mostrarError("Error con la base de datos", e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            mostrarError("Error al convetir", "Nose puede convertir el campo: " + e.getLocalizedMessage());
        } catch (RuntimeException e) {
            mostrarError("Error", e.getMessage());
        } finally {
            limpiarFormulario();
        }

    }

    @FXML
    private void onBuscarClick() {
        try {
            String nombre = campoBusqueda.getText();
            checkAndThrow(() -> emptyString(nombre), "Campo nombre vacio para buscar");
            Optional<Producto> buscado = repo.findByName(nombre);
            if (buscado.isEmpty()) throw new RuntimeException("No fue posible encontrar el producto");
            mostrarInfo("producto encontrado", buscado.get().toString());
        } catch (SQLException e) {
            mostrarError("Error con la base de datos", e.getLocalizedMessage());
        } catch (RuntimeException e) {
            mostrarInfo("Info", e.getMessage());
        }
    }

    @FXML
    private void onEliminarClick() {
        try {
            Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
            checkAndThrow(() -> seleccionado == null, "No hay producto seleccionado");
            Optional<Producto> encontrado = repo.findByName(seleccionado.getName());
            checkAndThrow(encontrado::isEmpty, "no se ha encontrado el producto a eliminar");
            if (!confirmar("Confirma", "¿deseas eliminar este usuario?")) {
                mostrarInfo("Operacion cancelada", "cancelaste la operacion para elimimnar al usuario");
                return;
            }
            if (repo.delete(encontrado.get().getId())) {
                mostrarInfo("Usuario eliminado", encontrado.get().toString());
                lista.remove(encontrado.get());
            } else {
                mostrarInfo("Error al eliminar", "No se pudo eliminar el usuario");
            }
        } catch (SQLException sql) {
            mostrarError("Error con la base de datos", sql.getLocalizedMessage());
        } catch (RuntimeException e) {
            mostrarInfo("Ocurrio un error", e.getMessage());
        }
    }


    @FXML
    private void onActualizarStockClick() {
        try {
            Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
            checkAndThrow(() -> seleccionado == null, "No hay producto seleccionado");
            Optional<Producto> encontrado = repo.findByName(seleccionado.getName());
            checkAndThrow(encontrado::isEmpty, "no se ha encontrado el producto a actualizar");
            int stock = Integer.parseInt(pedirTexto("Actualizar stock", "Ingresa el stock del campo a actualizar", "").orElse(""));
            checkAndThrow(() -> stock <= 0, "El stock debe ser mayor que cero");
            encontrado.get().setStock(stock);
            if (repo.update(encontrado.get())) {
                mostrarInfo("Producto actualizado", "Producto actualizado coon exito");
                onListarClick();
            } else {
                mostrarError("Error al actualizar", "error al actualizar producto");
            }
        } catch (SQLException e) {
            mostrarError("Error con la base de datos", e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            mostrarError("Error con la cantidad", e.getLocalizedMessage());
        } catch (RuntimeException e) {
            mostrarInfo("Error", e.getMessage());
        }
    }

    private void limpiarFormulario() {
        campoNombre.clear();
        campoPrecio.clear();
        campoStock.clear();
    }
}
