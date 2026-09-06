import java.util.HashMap;

public class OrderManager {
    private HashMap<Integer, Order> orders;

    public OrderManager(){
        this.orders = new HashMap<>();
    }
    public void addOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("Order cannot be null");

        }
        if(orders.containsKey(order.getId())){
            throw new IllegalArgumentException("Order already exists");
        }
        order.getCustomer().addOrder(order);
        orders.put(order.getId(), order);
    }
}