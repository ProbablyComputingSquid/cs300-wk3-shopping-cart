import jdk.jshell.execution.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;

public class Tests {
    static Store store = new Store();
    static Shopper shopper = new Shopper("S. Hopper");
    static PerishableItem apple = new PerishableItem(100, "apples", 2.00, 3, LocalDate.of(2026,8,25));
    static BeverageItem coke = new BeverageItem(101, "coke", 1.50, 5, true);
    static ElectronicsItem tv = new ElectronicsItem(102, "LG LED TV", 1999.99, 2, 6);
    static void testPerishableItem() {
        System.out.println("Testing the PerishableItem that is close to expiring, its file string, and final price. ");

        System.out.println("File String: " + apple.toFileString());
        System.out.println("Final price: $" + apple.calculateFinalPrice());
        System.out.println("Testing adding a PerishableItem that expires in the more distant future. ");
        PerishableItem pineapple = new PerishableItem(105, "pineapples", 5.00, 3, LocalDate.of(2026,7,25));
        System.out.println("Final price: $" + pineapple.calculateFinalPrice());
    }
    static void testBeverageItem() {
        System.out.println("Testing creation of a beverage item, its file string, and final price: ");

        System.out.println("File String: " + coke.toFileString());
        System.out.println("Final price: $" + coke.calculateFinalPrice());
    }
    static void testElectronicsItem() {
        System.out.println("Testing creation of an electronics item, it's file string, and final price. ");

        System.out.println("File String: " + tv.toFileString());
        System.out.println("Final price: $" + tv.calculateFinalPrice());
    }
    static void testAddingItemsToCart() {
        System.out.println("Adding valid item to cart...");
        BeverageItem coconutWater = new BeverageItem(117, "coconut water", 1.99, 100, false);
        System.out.println("Testing adding more coconut waters than are in stock...");
        System.out.println("You can add 10 coconut water to cart? " + coconutWater.canAdd(10));
        shopper.addItemToCart(coconutWater);
        shopper.addItemToCart(tv);
        shopper.addItemToCart(apple);
        shopper.addItemToCart(coke);
        System.out.println(shopper.generateReceipt());
    }
    static void testCheckout() {
        System.out.println("Testing system checkout");
        shopper.checkout();
        System.out.println("Shopper's receipt after checkout:");
        System.out.println(shopper.generateReceipt());
        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input = bufferedReader.readLine();
            while (input != null) {
                System.out.println(input);
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // TODO: implement tests
    static void testInvarianceSafety() {
        System.out.println(" Testing type invariance and safety: ");
        DataStore<ElectronicsItem> electronicsItemDataStore = new DataStore<>();
        // uncomment the following line to demonstrate type invariance
        //electronicsItemDataStore.add(new BeverageItem(1234, "coke", 1.00, 3, true));

    }
    static void testBoundedWildcardTotaling() {
        System.out.println(" Testing bounded wildcard totalling: ");
        DataStore<Item> cart = new DataStore<>();
        cart.add(apple);
        cart.add(coke);
        cart.add(tv);
        System.out.println(" Cart total: ");
        UtilityFunctions.displayCatalog(cart);
        System.out.printf("$%.2f %n", UtilityFunctions.calculateCartTotal(cart));
    }
    static void testGenericSearchRetrieval() {
        System.out.println("-- Testing generic search retrieval: --");
        System.out.println("Class of known perishable item 102 (avocado): " + store.findItem(102).getClass());
        System.out.println("Attempt to get unknown item 99999: " + store.findItem(99999));
        System.out.println("Class of known electronic item 116 (electric kettle) " +  store.findItem(116) + " " + store.findItem(116).getClass());
    }
    public static void main(String[] args) {
        //testPerishableItem();
        //testBeverageItem();
        //testElectronicsItem();
        //testAddingItemsToCart();
        //testCheckout();
        testInvarianceSafety();
        testBoundedWildcardTotaling();
        testGenericSearchRetrieval();
    }
}
