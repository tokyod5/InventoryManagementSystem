import java.io.Serializable;

public abstract class BaseProduct implements Serializable {
    public String id;         // Product ID
    public String name;       // Product Name
    public double price;      // Product Price
    public int quantity;      // Product Quantity

    // Constructor
    public BaseProduct(String id, String name, double price, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Common update logic (override in subclasses as needed)
    public void updateDetails(double newPrice, int newQuantity, String newExpiryDate) {
        if (newPrice > 0) {
            this.price = newPrice;
        }
        if (newQuantity >= 0) {
            this.quantity = newQuantity;
        }
    }

    // Abstract method to show product details
    public abstract void showInfo();
    
}
