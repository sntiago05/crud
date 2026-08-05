package productos.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conn {

    protected static String username = "santiago";
    protected static String password = "1234";
    protected static String databaseUrl = "jdbc:h2:file:./productos;MODE=PostgreSQL";


    private static Connection connection;

    private Conn() throws SQLException {

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null){
            Properties connectionProps = new Properties();
            connectionProps.put("user", username);
            connectionProps.put("password", password);
            connectionProps.put("serverTimezone", "UTC");
            connection = DriverManager.getConnection(databaseUrl, connectionProps);
        };
        return connection;
    }
}
