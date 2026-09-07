import java.util.ArrayList;
import java.util.HashMap;

public class CustomerManager {
    private HashMap<Integer, Customer> customers;


    public CustomerManager(){
        this.customers = new HashMap<>();

    }
    public void addCustomer(Customer customer){
        if(customer == null){
            throw new IllegalArgumentException("Customer cannot be null");

        }
        if(customers.containsKey(customer.getId())){
            throw new IllegalArgumentException("Customer already exists");
        }
        customers.put(customer.getId(), customer);
    }
    public Customer getCustomer(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Id must be positive");
        }
        return customers.get(id);
    }
    public boolean containsCustomer(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Id must be positive");
        }
        return customers.containsKey(id);
    }
    public boolean removeCustomer(int id){
        if(id <= 0){
            throw new IllegalArgumentException("Id must be positive");
        }
        return customers.remove(id) != null;
    }
    public ArrayList<Customer> getAllCustomers(){
        return new ArrayList<>(customers.values());
    }
    public int getCustomerCount(){
        return customers.size();
    }
}
