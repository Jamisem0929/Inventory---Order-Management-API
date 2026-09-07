import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private HashMap<Integer, Product> products;


    public Inventory(){
        this.products = new HashMap<>();
    }
    //product Adding and retrieving
    public void addProduct(Product product){
        if(product == null){
            throw new IllegalArgumentException("Product cannot be null");
        } 
        if (products.containsKey(product.getId())){
            throw new IllegalArgumentException("Product already exists");
        }
        products.put(product.getId(), product);
    }
    public Product getProduct(int id){
        return products.get(id);
    }
    public boolean containsProduct(int id){
        if( id <= 0 ){
            throw new IllegalArgumentException("Id must be positive");
        }
        return products.containsKey(id);
    }
    public boolean removeProduct(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Id must be positive");
        }
        return products.remove(id) != null;
    }
    public ArrayList<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }
    public int getProductCount(){
        return products.size();
    }
}
