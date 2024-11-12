public interface InventoryManager {
    void addItem(BaseProduct product);      // Add a product to the inventory
    void showAllItems();                    // Display all products in the inventory      // Save inventory to a file
    void loadFromFile(String fileName);     // Load inventory from a file
    void logAction(String action);          // Log an action to the history log
    void viewHistory();                     // View the history log
    void generateReport();                  // Generate a summary report of the inventory
    void updateProduct(String productId, double newPrice, int newQuantity, String newExpiryDate);
    void removeProduct(String productId);
         
}
