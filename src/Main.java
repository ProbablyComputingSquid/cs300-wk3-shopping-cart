/*
Functionality required

Your program should support the following use cases for customers through a menu in the main function that implements a command line interface  (similar to the menu we have implemented for the digital library example):

View & Filter Inventory: Display all available items in a clean tabular format (ID, Name, Price, Available Quantity).
Allow the user to search for items by a partial name string (case-insensitive).
Add Item to Cart: The user selects an item by its unique ID and specifies a quantity.
The system must verify that the requested quantity is available in inventory.csv before successfully adding it to the customer's virtual shopping Cart.
View Cart: Display all items currently in the cart, their quantities, line-item sub-totals, and the overall grand total cost of the cart.
Checkout: Deduct the purchased quantities permanently from the store's main inventory.
Clear the customer's shopping cart (note that one can only checkout items in one's shopping cart).
Append a receipt record to a transaction log file.
File Operations
The system must maintain persistence across restarts by handling two distinct files. Students must implement proper exception handling (try-catch blocks) for FileNotFoundException or IOException.

inventory.csv (Read & Write): Format: id,name,price,quantity (e.g., 101,Organic Bananas,1.99,50). Note that you should use inventory.csvDownload inventory.csv as the starting file for your tests.
Read: Loaded at system startup to populate the store's primary ArrayList<Item>.
Write: Overwritten entirely when a customer completes a checkout, saving the newly reduced inventory quantities back to disk.
transactions.txt (Append-Only):
Format: A human-readable text receipt appended to the end of the file upon a successful checkout.
Example Entry:


 */

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Greetings shopper, what is your name? ");
        String username = scanner.nextLine();
        Store store = new Store();

        Shopper shopper = new Shopper(username);
        boolean shopping = true;
        while (shopping) {
            System.out.println("--- Digital Grocery ---");
            System.out.println("""
                    1. View inventory
                    2. Search inventory
                    3. Add item to cart
                    4. View cart
                    5. Checkout
                    6. Leave Store
                    """);
            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case (1): // check the store's inventory
                    store.printInventory();
                    break;
                case (2): // search inventory for an item
                    System.out.println("Enter your search query: ");
                    String searchQuery = scanner.nextLine();
                    ArrayList<Item> searchedItems = store.searchInventory(searchQuery);
                    for (Item item : searchedItems) {
                        System.out.println(item);
                    }
                    break;
                case (3): // add item to cart
                    System.out.println("Add an item to your cart by typing it's ID, then the quantity of items. (e.g.) 102 2");
                    int idSelected = scanner.nextInt();
                    int quantitySelected = scanner.nextInt();

                    // after validating whether the withdrawal is allowed, perform it.
                    if (!store.itemExists(idSelected)) {
                        System.out.println("This item doesn't exist");
                        break;
                    }
                    if (!store.findItem(idSelected).canAdd(quantitySelected)) {
                        System.out.println("You can't add this amount to your cart!");
                        break;
                    }

                    shopper.addItemToCart(store.removeItemQuantity(idSelected,quantitySelected));

                    break;
                case (4): // check the shopper's current receipt
                    System.out.println("Here is your current receipt: ");
                    System.out.println(shopper.generateReceipt());
                    break;
                case (5): // checkout!!
                    shopper.checkout();
                    store.writeInventoryToFile();
                    break;
                case (6): // leave the store
                    shopping = false;
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid operation.");

            }

        }
    }
}
