package productos.crud.service;

import productos.crud.domain.Producto;
import productos.crud.repository.ProductRepository;
import productos.crud.repository.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static productos.crud.utils.HandleThrow.checkAndThrow;

public class ProductoService {

    private ProductRepository repo;

    public ProductoService(ProductRepository repo) {
        this.repo = repo;
    }

    public Optional<Producto> findByName(String name) throws SQLException {
        return repo.findByName(name);
    }

    public Optional<Producto> findById(Long id) throws SQLException {
        return repo.findById(id);
    }

    public List<Producto> findAll() throws SQLException {
        return repo.findAll();
    }

    public Boolean delete(Long id) throws SQLException {
        return repo.delete(id);
    }

    public Boolean update(Producto producto) throws SQLException {
        return repo.update(producto);
    }

    public Boolean save(Producto producto) throws SQLException {
        Optional<Producto> encontrado = repo.findByName(producto.getName());
        checkAndThrow(() -> encontrado.map(value -> value.equals(producto)).orElse(true), "El producto ya se encuentra registrado");
        return repo.save(producto);
    }
}
