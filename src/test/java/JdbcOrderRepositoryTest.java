import java.sql.SQLException;

public class JdbcOrderRepositoryTest {
    public static void main(String[] args)throws SQLException {
        CustomerRepository repo = new CustomerRepository();
        Customer customer = new Customer(2, "Bob", "Bob@example.com");
        repo.insertCustomer(customer);
        Order order = new Order(2, customer);
        JdbcOrderRepository repository = new JdbcOrderRepository();
        repository.insertOrder(order);
    }
}
