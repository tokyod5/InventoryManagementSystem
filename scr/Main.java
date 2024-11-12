import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);

        // Load inventory from file
        inventory.loadFromFile("inventory_data.dat");

        while (true) {
            // Main Menu
            System.out.println("\n=== Inventory Management ===\n");
            System.out.println("1. Add General Product");
            System.out.println("2. Add Expiry Product");
            System.out.println("3. Show All Items");
            System.out.println("4. Generate Inventory Report");
            System.out.println("5. View History");
            System.out.println("6. Remove Product");
            System.out.println("7. Update Product");
            System.out.println("8. Exit");
            System.out.print("\nChoose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Add General Product
                        System.out.println("\nAdding General Product:\n");
                        try {
                            System.out.print("Enter Product ID: ");
                            String id = scanner.nextLine();
                            System.out.print("\nEnter Name: ");
                            String name = scanner.nextLine();
                            System.out.print("\nEnter Price: ");
                            double price = scanner.nextDouble();
                            System.out.print("\nEnter Quantity: ");
                            int quantity = scanner.nextInt();

                            inventory.addItem(new GeneralProduct(id, name, price, quantity));
                        } catch (IllegalArgumentException e) {
                            
                            System.out.println("\n** Error: " + e.getMessage() + " **");
                            
                        }
                        break;

                    case 2:
                        // Add Expiry Product
                        System.out.println("\nAdding Expiry Product:\n");
                        try {
                            System.out.print("Enter Product ID: ");
                            String id = scanner.nextLine();
                            System.out.print("\nEnter Name: ");
                            String name = scanner.nextLine();
                            System.out.print("\nEnter Price: ");
                            double price = scanner.nextDouble();
                            System.out.print("\nEnter Quantity: ");
                            int quantity = scanner.nextInt();
                            System.out.print("\nEnter Expiry Date (YYYY-MM-DD): ");
                            scanner.nextLine(); // Consume newline
                            LocalDate expiryDate = LocalDate.parse(scanner.nextLine());

                            inventory.addItem(new ExpiryProduct(id, name, price, quantity, expiryDate));
                        } catch (IllegalArgumentException e) {
                            System.out.println("\n** Error: " + e.getMessage() + " **");
                        } catch (Exception e) {
                            System.out.println("\n** Error: Invalid expiry date format. **");
                        }
                        break;

                    case 3:
                        // Show All Items
                        System.out.println("\nShowing All Items:\n");
                        inventory.showAllItems();
                        break;

                    case 4:
                        // Generate Inventory Report
                        System.out.println("\nGenerating Inventory Report:\n");
                        inventory.generateReport();
                        break;

                    case 5:
                        // View History
                        System.out.println("\nViewing History:\n");
                        inventory.viewHistory();
                        break;

                    case 6:
                        // Remove Product
                        System.out.println("\nRemoving Product:\n");
                        System.out.print("Enter Product ID to remove: ");
                        String removeId = scanner.nextLine();
                        inventory.removeProduct(removeId);
                        break;

                    case 7:
                        // Update Product
                        System.out.println("\n=== Update Product ===\n");
                        System.out.println("1. Update General Product");
                        System.out.println("2. Update Expiry Product");
                        System.out.println("3. Back to Main Menu");
                        System.out.print("\nChoose an option: ");

                        int updateChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (updateChoice) {
                            case 1:
                                System.out.println("\nUpdating General Product:\n");
                                System.out.print("Enter Product ID: ");
                                String generalId = scanner.nextLine();
                                System.out.print("\nEnter New Price (or -1 to skip): ");
                                double generalPrice = scanner.nextDouble();
                                System.out.print("\nEnter New Quantity (or -1 to skip): ");
                                int generalQuantity = scanner.nextInt();

                                inventory.updateProduct(generalId, generalPrice, generalQuantity, null);
                                break;

                            case 2:
                                System.out.println("\nUpdating Expiry Product:\n");
                                System.out.print("Enter Product ID: ");
                                String expiryId = scanner.nextLine();
                                System.out.print("\nEnter New Price (or -1 to skip): ");
                                double expiryPrice = scanner.nextDouble();
                                System.out.print("\nEnter New Quantity (or -1 to skip): ");
                                int expiryQuantity = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                System.out.print("\nEnter New Expiry Date (YYYY-MM-DD, or leave blank if not applicable): ");
                                String expiryDate = scanner.nextLine();

                                inventory.updateProduct(expiryId, expiryPrice, expiryQuantity, expiryDate.isEmpty() ? null : expiryDate);
                                break;

                            case 3:
                                System.out.println("\nReturning to Main Menu...\n");
                                break;

                            default:
                                System.out.println("\n** Error: Invalid choice. Returning to Main Menu. **\n");
                        }
                        break;

                    case 8:
                        // Exit
                        System.out.println("\nGoodbye!\n");
                        return;

                    default:
                        System.out.println("\n** Error: Invalid choice. Please try again. **\n");
                }
            } catch (Exception e) {
                System.out.println("\n** Error: Invalid input. Please try again. **\n");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
