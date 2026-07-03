/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Week 5 Polymorphism shopping cart
 * Author:        Jacob Bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/22/26 ??? ive lost the date
 * Version:     3.3
 *             Main driving class for the shopping cart assignment

 * @see Item

 *******************************************************************************/
import java.util.Scanner;


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
                    DataStore<Item> searchedItems = store.searchInventory(searchQuery);
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
