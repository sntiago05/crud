package productos.crud.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    Optional<T> findById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    Boolean delete(ID id) throws SQLException;

    Boolean save(T entity) throws SQLException;
    Boolean update(T entity) throws  SQLException;
}


