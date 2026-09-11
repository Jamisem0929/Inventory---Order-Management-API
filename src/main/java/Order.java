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
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        this.customer = customer;
        this.id = id;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }
    public Order(int id, Customer customer, OrderStatus status) {
    if (id <= 0) {
        throw new IllegalArgumentException("Id must be greater than 0");
    }

    if (customer == null) {
        throw new IllegalArgumentException("Customer cannot be null");
    }

    if (status == null) {
        throw new IllegalArgumentException("Status cannot be null");
    }

        this.id = id;
        this.customer = customer;
        this.status = status;
        this.items = new ArrayList<>();
    }
    public Order(int id, Customer customer, OrderStatus status, ArrayList<OrderItem> items) {
    if (id <= 0) {
        throw new IllegalArgumentException("Id must be greater than 0");
    }

    if (customer == null) {
        throw new IllegalArgumentException("Customer cannot be null");
    }

    if (status == null) {
        throw new IllegalArgumentException("Status cannot be null");
    }
    if(items == null){
        throw new IllegalArgumentException("Items cannot be null");
    }

        this.id = id;
        this.customer = customer;
        this.status = status;
        this.items = new ArrayList<>(items);
    }

    // Getters
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getLineTotal());
        }
        return total;

    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public ArrayList<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public Customer getCustomer() {
        return customer;
    }

    // methods
    public void addItem(OrderItem item) {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Order can only be modified while pending");
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
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Order can only be modified while pending");
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

    public void placeOrder() {
        if (status == OrderStatus.PLACED) {
            throw new OrderAlreadyPlacedException("Order is already placed");
        }
        if (status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cancelled order cannot be placed");
        }
        if (items.isEmpty()) {
            throw new EmptyOrderException("Order cannot be empty");
        }
        if (!canBeFulfilled()) {
            throw new InsufficientStockException("Not enough stock to fulfill order");
        }
        for (OrderItem item : items) {
            item.getProduct().removeStock(item.getQuantity());
        }
        status = OrderStatus.PLACED;

    }

    public void cancelOrder() {
        if (status == OrderStatus.PENDING) {
            throw new IllegalStateException("Pending orders cannot be cancelled");
        }
        if (status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Order is already cancelled");
        }
        for (OrderItem item : items) {
            item.getProduct().addStock(item.getQuantity());
        }
        status = OrderStatus.CANCELLED;
    }
}
