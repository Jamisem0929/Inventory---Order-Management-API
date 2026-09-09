import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepositoryTest {
    public static void main(String[] args) throws SQLException {
        
    
        
        ProductRepository productRepository = new ProductRepository();
        productRepository.deleteProduct(3);
        productRepository.getProductById(3);
       }
    }
