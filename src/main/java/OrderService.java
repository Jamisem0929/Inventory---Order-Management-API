public class OrderService {
    private Inventory inventory;
    private CustomerManager customerManager;
    private OrderManager orderManager;
    public OrderService(
        Inventory inventory,
        CustomerManager customerManager,
        OrderManager orderManager
    ) {
        this.inventory = inventory;
        this.customerManager = customerManager;
        this.orderManager = orderManager;
    }
    public void addProductToOrder(int orderId, int productId, int quantity){
        Order order = orderManager.getOrder(orderId);
        Product product = inventory.getProduct(productId);
        if(order == null){
            throw new IllegalArgumentException("Order does not exist");
        }
        if(product == null){
            throw new IllegalArgumentException("Product does not exist");

        }
        OrderItem item = new OrderItem(product, quantity);
        order.addItem(item);
    }
    public Order createOrder(int orderId, int customerId){
        Customer customer = customerManager.getCustomer(customerId);
        if(customer == null){
            throw new IllegalArgumentException("Customer does not exist");
        }
        Order order = new Order(orderId, customer);
        orderManager.addOrder(order);
        return order;
    }
    public void placeOrder(int orderId){
        orderManager.placeOrder(orderId);
    }
    public void cancelOrder(int orderId){
        orderManager.cancelOrder(orderId);
    }
}
