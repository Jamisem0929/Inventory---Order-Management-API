import java.util.HashMap;

public class OrderRepository implements OrderStore {
    private HashMap<Integer, Order> orders;
    public OrderRepository(){
        this.orders = new HashMap<>();
    }
    @Override 
    public Order getOrder(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Id must be positive");
        }
        return orders.get(id);
    }
    @Override 
    public void addOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("Order cannot be null");
        }
        if(orders.containsKey(order.getId())){
            throw new IllegalArgumentException("Order already exists");
        }
        orders.put(order.getId(), order);
    }
    @Override 
    public boolean containsOrder(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Id must be positive");
        }
        return orders.containsKey(id);
    }
}
