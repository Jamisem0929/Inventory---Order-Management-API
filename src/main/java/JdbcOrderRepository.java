import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcOrderRepository {


    
    public int insertOrder(Order order) throws SQLException{
        String sql = "INSERT INTO orders (id, customer_id, status) VALUES(?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, order.getId());
                statement.setInt(2, order.getCustomer().getId());
                statement.setString(3, order.getStatus().name());
                return statement.executeUpdate();
            }
    }
}