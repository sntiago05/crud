import org.h2.tools.Console;
import org.h2.tools.RunScript;
import productos.crud.db.Conn;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;

public class InitializeDB {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        RunScript.execute(Conn.getConnection(),new FileReader("src/main/resources/sql/create.sql"));
        RunScript.execute(Conn.getConnection(),new FileReader("src/main/resources/sql/seeds.sql"));
    }
}
