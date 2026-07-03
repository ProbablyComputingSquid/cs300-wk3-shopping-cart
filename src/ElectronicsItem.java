/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Week 5 Polymorphism shopping cart
 * Author:        Jacob Bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/22/2026
 * Version:     1.3
 *              Electronics (Item subclass) which tracks eco fee and warranty months.

 * @see Item

 *******************************************************************************/
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
    @Override
    public String toString() {
        return super.toString() + String.format(" | Eco Fee: %.2f | Warranty Months: %d", getEcoFee(), getWarrantyMonths());
    }
    public int getWarrantyMonths() {
        return this.warrantyMonths;
    }
}
