/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Week 5 Polymorphism shopping cart
 * Author:        Jacob Bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/22/2026
 * Version:     3.2
 *              Shopper handling class which handles things and logic related to the shopper

 * @see Item
 * @see DataStore

 *******************************************************************************/

import java.io.*;
public class Shopper {
    private DataStore<Item> cart = new DataStore<>();
    private final String name; // jb says it should be final
    public Shopper(String name) {
        // nothing to be done here?
        this.name = name;
    }
    public String generateReceipt() {
        String receipt = "––– Transaction: " + name + " –––\n";
        int headerLength = receipt.length();
        // TODO: replace with displayCatalog
        for (Item item : cart) {
            String line = "%d x %s @ %.2f = %.2f\n";
            int quantity = item.getQuantity();
            double price = item.getPrice();
            double subtotal = item.calculateFinalPrice();
            line = String.format(line, quantity, item.getName(), price, subtotal);

            receipt += line;
        }
        receipt += String.format("Total: $%.2f\n", UtilityFunctions.calculateCartTotal(cart));
        String bottomLine = "";
        for (int i = 0; i < headerLength; i++) {
            bottomLine += "–"; // en dash to preserve width
        }
        receipt += bottomLine;
        return receipt;
    }
    public void addItemToCart(Item item) {
        cart.add(item);
        System.out.println("Successfully added " + item.getQuantity() + " " + item.getName() + " to your cart!");
    }
    private void writeReceipt() {
        try {
            FileWriter fileWriter = new FileWriter("transactions.txt", true);
            fileWriter.append(this.generateReceipt());
            fileWriter.close();
            System.out.println("Transactions appended to transactions.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // this function performs the checkout
    public void checkout() {
        // when the itme is checked out, it is already deducted from the store's inventory, so there is no need to do that anymore
        System.out.println("Checking out...");
        // write the receipt to file
        writeReceipt();
        // push the store's inventory to file (done elsewhere)
        // print out the final reciept
        System.out.println(generateReceipt());
        // clear the shopping cart
        this.cart.clear();

    }
}
