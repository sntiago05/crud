package productos.crud.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    Optional<T> findById(ID id);
    List<Optional<T>> findAll();
    Boolean delete(ID id);
    Boolean save(T entity);
}


