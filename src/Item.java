public class Item {

    // Fields
    private int id;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructor to initialize all attributes of a grocery item.
     */

    public Item(int id, String name, double price, int quantity) {
        setID(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }


    // --- Getters and Setters ---

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setID(int id) {
        this.id = id;
    }

    /**
     * Reduces the item's available stock inventory.
     *
     * @param amount The quantity to deduct.
     * @throws IllegalArgumentException if amount is negative or exceeds available quantity.
     */

    public void reduceStock(int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Reduction amount cannot be negative.");
        }

        if (amount > this.quantity) {
            throw new IllegalArgumentException("Insufficient stock available.");
        }

        this.quantity -= amount;

    }


    /**
     * Returns a comma-separated string formatted exactly for saving back to inventory.txt.
     * <p>
     * Format: id,name,price,quantity
     */

    public String toFileString() {
        return this.id + "," + this.name + "," + this.price + "," + this.quantity;
    }


    /**
     * Formats the item into a clean, human-readable layout for CLI display columns.
     * Example: "ID: 101  | Name: Organic Bananas       | Price: $1.99 | Stock: 50"
     */

    @Override

    public String toString() {
        return String.format("ID: %-5d | Name: %-20s | Price: $%-6.2f | Stock: %-4d",
                id, name, price, quantity);
    }
    /**
     * Calculates the item's final price
     * @return double price
     */
    public double calculateFinalPrice() {
        return 0;
    }
    /**
     * determines whether you can add x quantity of items to the cart
     * @return boolean
     */
    public boolean canAdd(int qty) {
        return false;
    }
}