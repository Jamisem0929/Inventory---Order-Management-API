import java.sql.Connection;
import java.sql.SQLException;

public class JdbcOrderRepositoryTest {
    public static void main(String[] args) throws SQLException {
        JdbcOrderRepository repository = new JdbcOrderRepository();
        Order order = repository.getOrderById(9999);

        if (order == null) {
            System.out.println("Order not found");
        } else {
            System.out.println(order.getId());
        }

    }

    
}
