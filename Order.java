import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList<OrderItem> items;
    private OrderStatus orderStatus;

    public Order(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        this.id = id;
        this.items = new ArrayList<>();
        this.orderStatus = PENDING;
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (OrderItem existingItem : items) {
            if (existingItem.getProduct().getId() == item.getProduct().getId()){
                throw new IllegalArgumentException("No duplicate items");
            }
        }
        items.add(item);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getLineTotal());
        }
        return total;

    }

    public int getId() {
        return id;
    }

    public ArrayList<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean removeItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        return items.remove(item);
    }

    public boolean canBeFulfilled() {
        if(items.isEmpty()){
            return false;
        }
        for (OrderItem item : items) {
            if (item.getProduct().getStock() < item.getQuantity()) {
                return false;
            }

        }
        return true;
    }
    public boolean placeOrder(){
        if (!canBeFulfilled()){
            return false;
        }
        for (OrderItem item : items){
            item.getProduct().removeStock(item.getQuantity());
        }
        return true;
    }
}
