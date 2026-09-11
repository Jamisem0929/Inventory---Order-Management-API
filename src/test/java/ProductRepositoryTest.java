import java.sql.SQLException;

public class ProductRepositoryTest {
    public static void main(String[] args) throws SQLException {



        ProductRepository productRepository = new ProductRepository();
        productRepository.deleteProduct(3);
        productRepository.getProductById(3);
       }
    }
