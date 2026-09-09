import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/inventory_order_management";
        String username = "postgres";
        String password = System.getenv("DB_PASSWORD");

        return DriverManager.getConnection(url, username, password);
    }
}
