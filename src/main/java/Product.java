import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int stock;

    public Product(int id, String name, BigDecimal price, int stock){
        if (id <= 0){
            throw new IllegalArgumentException("Id must be positive");
        }
        this.id = id;
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("There must be a name");
        }
        this.name = name;
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
        if (stock < 0){
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stock = stock;
    }
    //Getters
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public int getStock(){
        return stock;
    }
    //Stock Management
    public boolean isInStock(){
        return stock < 0;
    }
    public boolean addStock(int amount){
        if (amount >= 1){
            stock =  stock + amount;
            return true;
        }
        return false;
    }
    public boolean removeStock(int amount){
        if (amount <= 0 || amount > stock){
            return false;
        }
        stock = stock - amount;
        return true;
    }
}