import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository {

    public int insertProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (id, name, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getStock());

            return statement.executeUpdate();

        }

        }
            public Product getProductById(int id) throws SQLException{
                String sql = "SELECT * FROM products WHERE id = ?";

                try(
                    Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)
                )   {
                    statement.setInt(1, id);
                    ResultSet results = statement.executeQuery();
                    if(results.next()){
                        int productId = results.getInt("id");
                        String name = results.getString("name");
                        BigDecimal price = results.getBigDecimal("price");
                        int stock = results.getInt("stock");
                        return new Product(productId, name, price, stock);
                    }
                    return null;
            
        }
    }
}
