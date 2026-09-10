import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerRepository {

    public int insertCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (id, name, email) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());

            return statement.executeUpdate();
        }
    }

    public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    int customerId = results.getInt("id");
                    String name = results.getString("name");
                    String email = results.getString("email");
                    Customer customer = new Customer(customerId, name, email);
                    return customer;
                }
            }
            return null;
        }
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet results = statement.executeQuery()) {
            ArrayList<Customer> customers = new ArrayList<>();
            while (results.next()) {
                int customerId = results.getInt("id");
                String name = results.getString("name");
                String email = results.getString("email");
                Customer customer = new Customer(customerId, name, email);
                customers.add(customer);
            }
            return customers;
        }
    }
    public int updateCustomer(Customer customer) throws SQLException{
        String sql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, customer.getName());
                statement.setString(2, customer.getEmail());
                statement.setInt(3, customer.getId());
                return statement.executeUpdate();
            }
    }
    public int deleteCustomer(int id) throws SQLException{
        String sql = "DELETE FROM customers WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, id);
                return statement.executeUpdate();
        }
    }
}