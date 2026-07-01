public class UtilityFunctions {
    public static <T extends Item> void displayCatalog(DataStore<T> store) {
        for (Item item : store) {
            System.out.println(item); // polymorphically print out the item's toString
        }
    }
    public static double calculateCartTotal(DataStore<? extends Item> cart) {
        return 0;
// TODO!!
    }
}
