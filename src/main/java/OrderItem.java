import java.math.BigDecimal;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();

    }

    public OrderItem(Product product, int quantity, BigDecimal unitPrice) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (unitPrice == null) {
            throw new IllegalArgumentException("Unit price cannot be null");
        }

        if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative");
        }

        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getLineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
