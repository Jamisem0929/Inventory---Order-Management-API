public class OrderManager {
    private OrderStore orderStore;

    public OrderManager(OrderStore orderStore){
        this.orderStore = orderStore;
    }
    public void addOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("Order cannot be null");

        }
        if(orderStore.containsOrder(order.getId())){
            throw new IllegalArgumentException("Order already exists");
        }
        order.getCustomer().addOrder(order);
        orderStore.addOrder(order);
    }
    public void placeOrder(int id){
        Order order = orderStore.getOrder(id);
        if(order == null){
            throw new OrderNotFoundException("Order does not exist");
        }
        order.placeOrder();
    }
    public Order getOrder(int id){
       return orderStore.getOrder(id);
    }
    public void cancelOrder(int id){
        Order order = orderStore.getOrder(id);
        if( order == null){
            throw new OrderNotFoundException("Order does not exist");
        }
        order.cancelOrder();
    }
}