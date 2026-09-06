import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList<OrderItem> items;
    public Order(int id){
        if (id <= 0){
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        this.id = id;
        this.items = new ArrayList<>();
    }
}
