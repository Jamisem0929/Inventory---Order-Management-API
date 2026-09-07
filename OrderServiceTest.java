import java.math.BigDecimal;

public class OrderServiceTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        CustomerManager customerManager = new CustomerManager();
        OrderManager orderManager = new OrderManager();

        OrderService orderService = new OrderService(inventory, customerManager, orderManager);

        Product product  = new Product(1, "Keyboard", new BigDecimal("50.00"), 10);
        Customer customer = new Customer(1, "Alice", "Alice@example.com");

        inventory.addProduct(product);
        customerManager.addCustomer(customer);
        Order order = orderService.createOrder(1, 1);
        orderService.addProductToOrder(1, 1, 2);
        orderService.placeOrder(1);
        try {
            orderService.placeOrder(1);
        } catch(OrderAlreadyPlacedException e) {
            System.out.println(e.getMessage());
        }
        Order order2 = orderService.createOrder(2, 1);
        orderService.addProductToOrder(2, 1,9);
        try{
            orderService.placeOrder(2);
        } catch (InsufficientStockException e){
            System.out.println(e.getMessage());
        }
        Order order3 = orderService.createOrder(3, 1);
        try {
            orderService.placeOrder(3);
        } catch(EmptyOrderException e){
            System.out.println(e.getMessage());

        }
        orderService.cancelOrder(1);
        Order order4 = orderService.createOrder(4, 1);
        try {
            orderService.cancelOrder(4);   
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }

        try {
            orderService.cancelOrder(1);   
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }

        try {
            orderService.placeOrder(1);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(order.getStatus());
        System.out.println(product.getStock());
        
    }
    

}
