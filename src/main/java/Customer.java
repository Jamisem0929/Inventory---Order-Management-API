import java.util.ArrayList;

public class Customer {
    private int id;
    private String name;
    private String email;
    private ArrayList<Order> orders;

    public Customer(int id, String name, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be positive");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank or null");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank or null");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }
    //getters
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public ArrayList<Order> getOrders() {
        return new ArrayList<>(orders);
    }
    //methods
    public void addOrder(Order order){
        if(order == null){
            throw new IllegalArgumentException("Order cannot be null");

        }
        if(order.getCustomer() != this){
            throw new IllegalArgumentException("Order does not belong to this customer");
        }

        for (Order existingOrder : orders){
            if (existingOrder.getId() == order.getId()){
                throw new IllegalArgumentException("Order already exists");
            }

        }
        orders.add(order);
    }
}
