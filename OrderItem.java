import java.math.BigDecimal;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal unitPrice;
 
    public OrderItem(Product  product, int quantity){
        if (product == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        
        if (quantity < 1){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();

    }
    public Product getProduct(){
        return product;
    }
    public int getQuantity(){
        return quantity;
    }
    public BigDecimal getUnitPrice(){
        return unitPrice;
    }
    public BigDecimal getLineTotal(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
