package productos.crud.repository;

import productos.crud.domain.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static productos.crud.utils.HandleThrow.checkAndThrow;

public class ProductRepositoryImpl implements ProductRepository {
    private Connection conn;

    public ProductRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Producto> findByName(String name) throws SQLException {
        try (
                PreparedStatement pst = conn.prepareStatement("select * from Productos where name = ?")) {
            pst.setString(1, name);
            try {
                ResultSet rs = pst.executeQuery();
                return mapProduct(rs);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static Optional<Producto> mapProduct(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        if (!rs.next()) return Optional.empty();
        producto.setName(rs.getString("name"));
        producto.setId(rs.getLong("id"));
        producto.setStock(rs.getInt("stock"));
        producto.setPrice(rs.getBigDecimal("price"));
        return Optional.of(producto);
    }

    @Override
    public Optional<Producto> findById(Long a) throws SQLException {
        try (
                PreparedStatement pst = conn.prepareStatement("select * Productos where id =?")) {
            try {
                ResultSet rs = pst.executeQuery();
                return mapProduct(rs);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("select * from Productos");
            List<Producto> productos = new ArrayList<>();
            while (rs.next()) {
                productos.add(mapProductCurrentRow(rs));
            }
            return productos;
        }
    }

    @Override
    public Boolean delete(Long id) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement("delete from Productos where id = ?")) {
            pst.setLong(1, id);
            int rows = pst.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public Boolean save(Producto entity) throws SQLException {
        try (
                PreparedStatement pst = conn.prepareStatement("insert into Productos (name,price,stock) VALUES (?,?,?)")) {
            pst.setString(1, entity.getName());
            pst.setBigDecimal(2, entity.getPrice());
            pst.setInt(3, entity.getStock());
            int rows = pst.executeUpdate();
            return rows > 1;
        }
    }

    @Override
    public Boolean update(Producto entity) throws SQLException {
        String sql = "UPDATE Productos SET stock = ? WHERE id = ?";
        try (PreparedStatement pst = this.conn.prepareStatement(sql)) {
            pst.setInt(1, entity.getStock());
            pst.setLong(2, entity.getId());
            int rows = pst.executeUpdate();
            return rows > 0;
        }
    }

    private static Producto mapProductCurrentRow(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setName(rs.getString("name"));
        producto.setId(rs.getLong("id"));
        producto.setStock(rs.getInt("stock"));
        producto.setPrice(rs.getBigDecimal("price"));
        return producto;
    }
}
