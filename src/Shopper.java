import java.util.ArrayList;
import java.io.*;
public class Shopper {
    // specifications:
    // must have a cart attribute, which it can view (using item view) and display the total cost of the car
    // should display like 2x bananas if there are 2 bananas, their individual total and their subtotal
    // should also have an addCart function (which checks if the inventory is available, then adds it to the receipt).
    // addCart selects by ID and quantity
    // checkout function which writes the current store inventory permanently to the file.
    private ArrayList<Item> cart = new ArrayList<>();
    private final String name; // jb says it should be final
    public Shopper(String name) {
        // nothing to be done here?
        this.name = name;
    }
    public String generateReceipt() {
        String receipt = "––– Transaction: " + name + " –––\n";
        int headerLength = receipt.length();
        double total = 0.0;
        for (Item item : cart) {
            String line = "%d x %s @ %.2f = %.2f\n";
            int quantity = item.getQuantity();
            double price = item.getPrice();
            double subtotal = item.calculateFinalPrice();
            line = String.format(line, quantity, item.getName(), price, subtotal);

            receipt += line;
            total += subtotal;
        }
        receipt += "Total: $" + total + "\n";
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
