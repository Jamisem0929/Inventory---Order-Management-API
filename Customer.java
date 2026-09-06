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
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
}
