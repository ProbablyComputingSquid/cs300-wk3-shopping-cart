/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Week 6 Performance Testing shopping data structures
 * Author:        Jacob Bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  7/15/2026
 * Version:     1.0
 *               various test functions

 * @see Item
 * @see DataStore

 *******************************************************************************/
public class Tests {
    // TODO
    public Item binarySearch(Store storeSearched, int itemId) {

    }

    static Store arrayListStore = new Store("products_random_id_100k.csv", "A");
    static Shopper shopper = new Shopper("Samuel Hopper");

    // Test case I: test the time to make 200,000 searches (using a random id as the key in each search,
    // and the search should be linear since the data is unsorted) for both ArrayList and LinkedList.
    // Record the time and print the result. Note that the number 200,000 can be adjusted according to your computer speed.
    private static void testNSearches(int searches, Store storeUsed) {
        System.out.println("Test case 1: Testing " + searches + " random searches...");
        long start = System.currentTimeMillis();
        for (int i = 0; i < searches; i++) {
            storeUsed.findItem((int) (Math.random() * 100001) + 101);
            if (i % 10000 == 0) {
                System.out.println(i + " searches completed");
            }
        }
        long timeElapsed = (System.currentTimeMillis() - start);
        System.out.println("Tested " + searches + " searches. It took " + timeElapsed + "ms.");
    }
    /*
    i. Sort both the LinkedList and ArrayList of Item objects\
      that you have created out of the file provided using calls like "Collections.sort(itemList);"
       with itemList being the ArrayList or LinkedList  that you have created.
ii. Write your own version of binarySearch method (using the generic List as the member type) using recursion
(refer to Week 6 slides, but you need to use List, not array).
iii. Call the binarySearch method for 200,000 searches (adjust this number if necessary) on both LinkedList and ArrayList.
Record the performance.
     */
    private static void sortStoreInventory(Store store) {
        System.out.println("Test case 2.1: Testing sorting.");
        long start = System.currentTimeMillis();
        store.sortInventory();
        long timeElapsed = System.currentTimeMillis() - start;
        System.out.println("Tested sorting. It took " + timeElapsed + "ms.");
    }
    // min id is 101
    // max id is 100100
    public static void main(String[] args) {
        // find the min and max id
        int min = 100000;
        int max = 0;
        int id;
        for (Item item : arrayListStore.getInventory()) {
            id = item.getId();
            if (id < min) {
                min = id;
            }
            if (id > max) {
                max = id;
            }
        }
        System.out.println("Min id: " + min);
        System.out.println("Max id: " + max);

        System.out.println("ArrayList tests:");
        testNSearches(100_000, arrayListStore);
        sortStoreInventory(arrayListStore);

    }
}
