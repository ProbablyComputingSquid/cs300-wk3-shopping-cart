public class BeverageItem extends Item{
    private boolean isCarbonated;

    public BeverageItem(int id, String name, double price, int quantity, boolean isCarbonated) {
        super(id, name, price, quantity);
        this.isCarbonated = isCarbonated;
    }
    public boolean getCarbonation() {
        return isCarbonated;
    }
    public void setCarbonated(boolean carbonation) {
        this.isCarbonated = carbonation;
    }
    public double getBottleDeposit() {
        return isCarbonated ? 0.1 : 0;
    }
    @Override
    public double calculateFinalPrice() {
        return getPrice() + getBottleDeposit();
    }
}
