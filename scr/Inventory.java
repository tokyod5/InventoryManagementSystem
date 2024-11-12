import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Inventory implements InventoryManager {
    public ArrayList<BaseProduct> items = new ArrayList<>();
    public String dataFile = "inventory_data.dat";
    public String historyFile = "history.log";

    // Check if a product ID already exists
    public boolean productIdExists(String productId) {
        for (BaseProduct product : items) {
            if (product.id.equals(productId)) {
                return true;
            }
        }
        return false;
    }

    // Add a product to the inventory
    @Override
    public void addItem(BaseProduct product) {
        if (productIdExists(product.id)) {
            
            System.out.println("===================================================================");
            System.out.println("\nError: A product with ID " + product.id + " already exists in the inventory.");
            System.out.println("===================================================================");
            return;
        }

        if (product.quantity < 0) {
            System.out.println("\nError: Quantity cannot be negative.");
            return;
        }

        items.add(product);
        System.out.println("\nItem added successfully!");
        logAction("Added product: " + product.name);
        saveToFile();
    }

    // Show all items in the inventory
    @Override
    public void showAllItems() {
        if (items.isEmpty()) {
            System.out.println("\nNo items in the inventory.");
            return;
        }
        System.out.println("\n=== Inventory Items ===\n");
        for (BaseProduct product : items) {
            product.showInfo();
            System.out.println("----------------------------");
        }
    }

    // Load inventory from a file
    @Override
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            items = (ArrayList<BaseProduct>) ois.readObject();
            System.out.println("\nInventory loaded from file.");
            logAction("Loaded inventory from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\nError: Unable to load inventory. File may not exist or is corrupted.");
        }
    }

    // Save the inventory to a file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(items);
        } catch (IOException e) {
            System.out.println("\nError: Unable to save inventory.");
        }
    }

    // Log an action to the history file
    @Override
    public void logAction(String action) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile, true))) {
            String timestamp = LocalDate.now().toString();
            writer.write("[" + timestamp + "] " + action);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("\nError: Unable to write to history log.");
        }
    }

    // View the history log
    @Override
    public void viewHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(historyFile))) {
            System.out.println("\n=== Inventory History Log ===\n");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("\nError: Unable to read history log. File may not exist.");
        }
    }

    // Generate a summary report of the inventory
    @Override
    public void generateReport() {
        double totalValue = 0;
        int lowStockCount = 0;

        for (BaseProduct product : items) {
            totalValue += product.price * product.quantity;
            if (product.quantity < 5) {
                lowStockCount++;
            }
        }

        System.out.println("\n=== Inventory Report ===\n");
        System.out.println("Total Items: " + items.size());
        System.out.println("Low Stock Items: " + lowStockCount);
        System.out.println("Total Inventory Value: $" + totalValue);
    }

    // Update a product in the inventory
    @Override
    public void updateProduct(String productId, double newPrice, int newQuantity, String newExpiryDate) {
        for (BaseProduct product : items) {
            if (product.id.equals(productId)) {
                product.updateDetails(newPrice, newQuantity, newExpiryDate);
                System.out.println("\nProduct updated successfully: " + product.name);
                logAction("Updated product: " + product.name);
                saveToFile();
                return;
            }
        }
        System.out.println("\nError: Product with ID " + productId + " not found.");
    }

    // Remove a product from the inventory
    @Override
    public void removeProduct(String productId) {
        BaseProduct productToRemove = null;
        for (BaseProduct product : items) {
            if (product.id.equals(productId)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            items.remove(productToRemove);
            System.out.println("\nProduct removed successfully: " + productToRemove.name);
            logAction("Removed product: " + productToRemove.name);
            saveToFile();
        } else {
            System.out.println("\nError: Product with ID " + productId + " not found.");
        }
    }
}
