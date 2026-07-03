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

import java.time.LocalDate;
import java.io.*;

public class Store {
    private DataStore<Item> inventory = new DataStore<>();
    public boolean readInventoryFile(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); // skip first column
            String input = bufferedReader.readLine();
            //System.out.println("Input: " + input);

            while (input != null) { // loop through the lines of the inventory
                //System.out.println(input);
                String[] splitInput = input.split(",");
                // format: id,name,price,quantity
                String classType = splitInput[0];
                int id = Integer.parseInt(splitInput[1]);
                String name = splitInput[2];
                double price = Double.parseDouble(splitInput[3]);
                int quantity = Integer.parseInt(splitInput[4]);
                // class creation;
                Item item;
                switch (classType) {
                    case "P": // perishable item
                        LocalDate expirationDate = LocalDate.parse(splitInput[5]);
                        item = new PerishableItem(id,name,price,quantity,expirationDate);
                        break;
                    case "B": // beverage item
                        boolean isCarbonated = Boolean.parseBoolean(splitInput[5]);
                        item = new BeverageItem(id, name, price, quantity, isCarbonated);
                        break;
                    case "E": // electronics item
                        int warrantyMonths = Integer.parseInt(splitInput[5]);
                        item = new ElectronicsItem(id, name, price, quantity, warrantyMonths);
                        break;
                    default:
                        System.out.println("Invalid entry found: " + input);
                        item = null;
                        // this should never happen
                }
                //Item item = new Item(id,name,price,quantity);
                inventory.add(item);
                input = bufferedReader.readLine();
            }
            return true;
        } catch (NullPointerException n) {
            System.out.println("Could not find file " + file);
            return false;
        } catch (Exception e) {
            System.out.println("Make sure inventory is in the correct format");
            e.printStackTrace();
            return false;
        }
    }

    void writeInventoryToFile() {
        try {
            StringBuilder inventoryCSV = new StringBuilder();
            inventoryCSV.append("Type,ID,Name,BasePrice,Quantity,SubclassAttribute\n");
            for (Item item : inventory) {
                inventoryCSV.append(item.toFileString() + "\n");
            }
            //System.out.println(inventoryCSV.toString());
            FileWriter fileWriter = new FileWriter("inventory.csv", false);
            fileWriter.write(inventoryCSV.toString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printInventory() {
        for (Item item : inventory) {
            System.out.println(item);
        }
    }
    /**
     Searches the store's inventory for item(s) matching the query (case-insensitive).
     @return DataStore of items that match the search query
      * */
    public DataStore<Item> searchInventory(String query) {
        DataStore<Item> itemDataStore = new DataStore<>();
        query = query.toLowerCase().strip();
        for (Item item : inventory) {
            if (item.getName().toLowerCase().contains(query)) {
                itemDataStore.add(item);
            }
        }
        if (itemDataStore.isEmpty()) {
            System.out.println("Could not find any items matching query: " + query);
        }
        return itemDataStore;
    }
    /**
     * Method that returns an Item based on it's id
     * @param id id of the item
     */
    public Item findItem(int id) {
        for (Item item: inventory) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    /**
     * Method that checks if the id and quantity can be withdrawn from the inventory
     * @param id id of item
     * @param quantity amount of item
     * @return boolean, see above
     */
    public boolean canWithdraw(int id, int quantity) {

        for (Item item : inventory) {
            if (item.getId() == id) {
                if (item.canAdd(quantity)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean itemExists(int id) {
        for (Item item : inventory) {
            if (item.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public Item removeItemQuantity(int id, int quantity) {
        // this must edit the Item entry in the list, and remove the quantity specified fronm the inventory list.
        // then, it must return a new Item that contains the items checked out
        // the id and quantity are validated
        if (!this.itemExists(id)) {
            System.out.printf("We don't carry an item with id %d\n", id);
            return null;
        }
        if (!this.canWithdraw(id, quantity)) {
            System.out.printf("We don't have %d of this item in stock!\n", quantity);
            return null;
        }
        for (Item item : inventory) {
            if (item.getId() == id) {
                // remove the quantity of item from the list

                Item newItem;
                if (item instanceof PerishableItem) {
                    newItem = new PerishableItem(item.getId(), item.getName(), item. getPrice(), item.getQuantity(), ((PerishableItem) item).getExpirationDate());
                } else if (item instanceof BeverageItem) {
                    newItem = new BeverageItem(item.getId(), item.getName(), item. getPrice(), item.getQuantity(), ((BeverageItem) item).getCarbonation());
                } else if (item instanceof ElectronicsItem) {
                    newItem = new ElectronicsItem(item.getId(), item.getName(), item. getPrice(), item.getQuantity(), ((ElectronicsItem) item).getWarrantyMonths());
                } else {
                    return null;
                }

                newItem.setQuantity(quantity);
                item.setQuantity(item.getQuantity()-quantity);

                // new item to be returned
                return newItem;
            }
        }
        return null; // this should never happen because the input is validated prior
    }



    public Store() {
        // fetch inventory from inventory.csv
        boolean result = readInventoryFile("inventory.csv");
        if (result) {
            System.out.println("Inventory successfully loaded!");
        }
    }
}
