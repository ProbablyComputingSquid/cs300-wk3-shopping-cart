public class ElectronicsItem extends Item {
    private int warrantyMonths;

    public ElectronicsItem(int id, String name, double price, int qty, int warrantyMonths) {
        super(id, name, price, qty);
        this.warrantyMonths = warrantyMonths;
    }
    public double getEcoFee() {
        return 5.00;
    }
    @Override
    public double calculateFinalPrice() {
        return (getEcoFee() + this.getPrice()) * this.getQuantity();
    }
    @Override
    public boolean canAdd(int qty) {
        return !(qty > this.getQuantity() || qty > 2) && qty > 0;
    }
    @Override
    public String toFileString() {
        return "E," + super.toFileString() + "," + this.getWarrantyMonths();
    }

    public int getWarrantyMonths() {
        return this.warrantyMonths;
    }
}
