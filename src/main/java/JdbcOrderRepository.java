import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcOrderRepository {

    public int insertOrder(Order order) throws SQLException {
    String sql = "INSERT INTO orders (id, customer_id, status) VALUES (?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection()) {
        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getCustomer().getId());
            statement.setString(3, order.getStatus().name());

            int rowsAffected = statement.executeUpdate();

            insertOrderItems(connection, order);

            connection.commit();

            return rowsAffected;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}

    public Order getOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    int orderId = results.getInt("id");
                    int customerId = results.getInt("customer_id");
                    String status = results.getString("status");

                    CustomerRepository customerRepository = new CustomerRepository();
                    Customer customer = customerRepository.getCustomerById(customerId);

                    ArrayList<OrderItem> items = getOrderItemsByOrderId(orderId);

                    Order order = new Order(
                            orderId,
                            customer,
                            OrderStatus.valueOf(status),
                            items);

                    return order;
                }
            }
        }

        return null;
    }

    private ArrayList<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, orderId);

            try (ResultSet results = statement.executeQuery()) {
                ArrayList<OrderItem> items = new ArrayList<>();
                ProductRepository productRepository = new ProductRepository();

                while (results.next()) {
                    int productId = results.getInt("product_id");
                    int quantity = results.getInt("quantity");
                    BigDecimal unitPrice = results.getBigDecimal("unit_price");

                    Product product = productRepository.getProductById(productId);

                    OrderItem orderItem = new OrderItem(
                            product,
                            quantity,
                            unitPrice);

                    items.add(orderItem);
                }

                return items;
            }
        }
    }

    private void insertOrderItems(Connection connection, Order order) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        for (OrderItem item : order.getItems()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, order.getId());
                statement.setInt(2, item.getProduct().getId());
                statement.setInt(3, item.getQuantity());
                statement.setBigDecimal(4, item.getUnitPrice());
                statement.executeUpdate();
            }
        }

    }
}
