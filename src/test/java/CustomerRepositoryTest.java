import java.sql.SQLException;

public class CustomerRepositoryTest {
    public static void main(String[] args) throws SQLException{
        CustomerRepository repository = new CustomerRepository();
        
       repository.deleteCustomer(2);
    }
}
