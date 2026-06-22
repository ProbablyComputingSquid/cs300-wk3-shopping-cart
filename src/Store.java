import java.util.ArrayList;
import java.io.*;

public class Store {
    private ArrayList<Item> inventory = new ArrayList<>();
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
                int id = Integer.parseInt(splitInput[0]);
                String name = splitInput[1];
                double price = Double.parseDouble(splitInput[2]);
                int quantity = Integer.parseInt(splitInput[3]);
                Item item = new Item(id,name,price,quantity);
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
            inventoryCSV.append("id,name,price,quantity\n");
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
     @return ArrayList of items that match the search query
      * */
    public ArrayList<Item> searchInventory(String query) {
        ArrayList<Item> itemsArrayList = new ArrayList<>();
        query = query.toLowerCase().strip();
        for (Item item : inventory) {
            if (item.getName().toLowerCase().contains(query)) {
                itemsArrayList.add(item);
            }
        }
        if (itemsArrayList.isEmpty()) {
            System.out.println("Could not find any items matching query: " + query);
        }
        return itemsArrayList;
    }

    /**
     * Method that checks if the id and quantity can be withdrawn from the inventory
     * @param id id of item
     * @param quantity amount of item
     * @return boolean, see above
     */
    public boolean canWithdraw(int id, int quantity) {
        if (quantity <= 0) {
            System.out.println("Buy something at least!");
            return false;
        }
        for (Item item : inventory) {
            if (item.getId() == id) {
                if (item.getQuantity() >= quantity) {
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
    public Item removeItem(int id, int quantity) {
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
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getId() == id) {
                Item oldItem = inventory.get(i);
                int oldQuantity = oldItem.getQuantity();
                String oldName = oldItem.getName();
                double oldPrice = oldItem.getPrice();
                Item updatedEntry = new Item(id, oldName, oldPrice, oldQuantity - quantity);
                inventory.set(i, updatedEntry);
                Item newItem = new Item(id, oldName, oldPrice, quantity); // new item to be returned
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
