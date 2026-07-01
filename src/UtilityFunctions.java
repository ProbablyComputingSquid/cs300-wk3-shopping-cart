public class UtilityFunctions {
    public static <T extends Item> void displayCatalog(DataStore<T> store) {
        for (Item item : store) {
            System.out.println(item); // polymorphically print out the item's toString
        }
    }
    public static double calculateCartTotal(DataStore<? extends Item> cart) {
        double total = 0;
        for (Item item : cart) {
            total += item.calculateFinalPrice();
        }
        return total;
    }
}
