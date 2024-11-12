import java.time.LocalDate;

public class ExpiryProduct extends BaseProduct {
    public LocalDate expiryDate; // Expiry date for the product

    // Constructor
    public ExpiryProduct(String id, String name, double price, int quantity, LocalDate expiryDate) {
        super(id, name, price, quantity);
        this.expiryDate = expiryDate;
    }

    // Override updateDetails to handle expiry date updates
    @Override
    public void updateDetails(double newPrice, int newQuantity, String newExpiryDate) {
        super.updateDetails(newPrice, newQuantity, null); // Update common fields

        if (newExpiryDate != null && !newExpiryDate.isEmpty()) {
            try {
                this.expiryDate = LocalDate.parse(newExpiryDate);
                System.out.println("Expiry date updated successfully.");
            } catch (Exception e) {
                System.out.println("Error: Invalid expiry date format.");
            }
        }
    }

    // Display product details
    @Override
    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Expiry Date: " + expiryDate);
    }
}
