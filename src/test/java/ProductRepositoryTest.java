import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ProductRepositoryTest {
    public static void main(String[] args) throws SQLException {
        
    
        
        ProductRepository productRepository = new ProductRepository();
        Product product2 = productRepository.getProductById(6);
        System.out.println(product2.getId());
        System.out.println(product2.getName());
        System.out.println(product2.getPrice());
        System.out.println(product2.getStock());
    }
    
}
