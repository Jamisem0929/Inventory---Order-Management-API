public interface OrderStore {
    void addOrder(Order order);
    Order getOrder(int id);
    boolean containsOrder(int id);
}
