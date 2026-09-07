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
    public void placeOrder(int id){
        Order order = orders.get(id);
        if(order == null){
            throw new OrderNotFoundException("Order does not exist");
        }
        order.placeOrder();
    }
    public Order getOrder(int id){
        if(id <= 0 ){
            throw new IllegalArgumentException("Id must be positive");

        }
        return orders.get(id);
    }
    public void cancelOrder(int id){
        Order order = orders.get(id);
        if( order == null){
            throw new OrderNotFoundException("Order does not exist");
        }
        order.cancelOrder();
    }
}