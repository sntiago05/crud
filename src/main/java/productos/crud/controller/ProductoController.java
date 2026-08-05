package productos.crud.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import productos.crud.db.Conn;
import productos.crud.domain.Producto;
import productos.crud.repository.ProductRepository;
import productos.crud.repository.ProductRepositoryImpl;
import productos.crud.service.ProductoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static productos.crud.utils.Alertas.mostrarInfo;

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
        List<Optional<Producto>> list = repo.findAll();
        if (list.isEmpty()) {
            mostrarInfo("Lista vacia", "La lista no tiene productos");
            return;
        }
        lista.addAll(list.stream().map(Optional::get).toList());
    }

    @FXML
    private void onRegistrarClick() {


    }

    @FXML
    private void onBuscarClick() {

    }

    @FXML
    private void onEliminarClick() {

    }


    @FXML
    private void onActualizarStockClick() {

    }

    private void limpiarFormulario() {
        campoNombre.clear();
        campoPrecio.clear();
        campoStock.clear();
    }
}
