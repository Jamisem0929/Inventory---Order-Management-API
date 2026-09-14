import java.sql.SQLException;

public class JdbcOrderRepositoryTest {
    public static void main(String[] args) throws SQLException {
        JdbcOrderRepository repository = new JdbcOrderRepository();
        Order order = repository.getOrderById(1);

        System.out.println(order.getId());
        System.out.println(order.getStatus());
        System.out.println(order.getItems().size());
        for (OrderItem item : order.getItems()) {
            System.out.println(item.getProduct().getName());
            System.out.println(item.getQuantity());
            System.out.println(item.getUnitPrice());
        }
    }
}
