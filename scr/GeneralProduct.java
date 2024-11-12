public class GeneralProduct extends BaseProduct {

    // Constructor
    public GeneralProduct(String id, String name, double price, int quantity) {
        super(id, name, price, quantity);
    }

    // Display product details
    @Override
    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
    }
}
