/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Week 5 Polymorphism shopping cart
 * Author:        Jacob Bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  7/2/2026
 * Version:     1.0
 *             utility functions handling class

 * @see Item
 * @see DataStore

 *******************************************************************************/

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
