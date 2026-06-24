import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Tests {
    static Store store = new Store();
    static Shopper shopper = new Shopper("S. Hopper");
    static void testPerishableItem() {
        System.out.println("Testing the PerishableItem class, its file string, and final price. ");
        PerishableItem apple = new PerishableItem(100, "apples", 2.00, 3, LocalDate.of(2026,6,25));
        System.out.println(apple.toFileString());
        System.out.println(apple.calculateFinalPrice());
    }
    static void testBeverageItem() {
        System.out.println("Testing creation of a beverage item, its file string, and final price: ");
        BeverageItem coke = new BeverageItem(101, "coke", 1.99, 5, true);
        System.out.println(coke.toFileString());
        System.out.println(coke.calculateFinalPrice());
    }
    static void testElectronicsItem() {
        System.out.println("Testing creation of an electronics item, it's file string, and final price. ");
        ElectronicsItem tv = new ElectronicsItem(102, "LG LED TV", 1999.99, 5, 6);
        System.out.println(tv.toFileString());
        System.out.println(tv.calculateFinalPrice());
    }
    static void testAddingItemsToCart() {
        System.out.println("Adding valid item to cart...");
        Item coconutWater = store.removeItem(117, 5);
        System.out.println("Testing adding more coconut waters than are in stock...");
        System.out.println("You can add 10 coconut water to cart? " + coconutWater.canAdd(10));
        shopper.addItemToCart(coconutWater);
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
    static void main(String[] args) {
        testPerishableItem();
        testBeverageItem();
        testElectronicsItem();
        testAddingItemsToCart();
        testCheckout();
    }
}
