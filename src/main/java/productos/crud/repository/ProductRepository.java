package productos.crud.repository;

import productos.crud.domain.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Repository<Producto, Long> {

    Optional<Producto> findByName(String name) throws SQLException;

    @Override
    Optional<Producto> findById(Long aLong);

    @Override
    List<Optional<Producto>> findAll();

    @Override
    Boolean delete(Long aLong);

    @Override
    Boolean save(Producto entity);
}
