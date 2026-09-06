import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList<OrderItem> items;
    private OrderStatus status;
    private Customer customer;

    public Order(int id, Customer customer) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        if(customer == null){
            throw new IllegalArgumentException("Customer cannot be null");
        }
        this.customer = customer;
        this.id = id;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }
    //Getters
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getLineTotal());
        }
        return total;

    }
    public OrderStatus getStatus(){
        return status;
    }
    public int getId() {
        return id;
    }

    public ArrayList<OrderItem> getItems() {
        return new ArrayList<>(items);
    }
    public Customer getCustomer(){
        return customer;
    }

    // methods
     public void addItem(OrderItem item) {
        if( status == OrderStatus.PLACED){
            throw new IllegalStateException("Order is already placed");
        }
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (OrderItem existingItem : items) {
            if (existingItem.getProduct().getId() == item.getProduct().getId()) {
                throw new IllegalArgumentException("No duplicate items");
            }
        }
        items.add(item);
    }  


    public boolean removeItem(OrderItem item) {
         if( status == OrderStatus.PLACED){
            throw new IllegalStateException("Order is already placed");
        }
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        return items.remove(item);
    }

    public boolean canBeFulfilled() {
        if (items.isEmpty()) {
            return false;
        }
        for (OrderItem item : items) {
            if (item.getProduct().getStock() < item.getQuantity()) {
                return false;
            }

        }
        return true;
    }

    public boolean placeOrder() {
        if (status == OrderStatus.PLACED) {
            return false;
        }
        if (!canBeFulfilled()) {
            return false;
        }
        for (OrderItem item : items) {
            item.getProduct().removeStock(item.getQuantity());
        }
        status = OrderStatus.PLACED;
        return true;
    }
}
