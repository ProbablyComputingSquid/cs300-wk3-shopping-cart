/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Week 5 Polymorphism shopping cart
 * Author:        Jacob Bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/22/2026
 * Version:     1.2
 *              Beverage Item subclass which tracks carbonation, etc.

 * @see Item

 *******************************************************************************/
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
        return (getPrice() + getBottleDeposit()) * getQuantity();
    }
    @Override
    public boolean canAdd(int qty) {
        return this.getQuantity() >= qty && qty > 0;
    }
    @Override
    public String toFileString() {
        return "B," + super.toFileString() + "," + this.getCarbonation();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Carbonation: %-5b | Bottle Deposit: $%.2f ",
                getCarbonation(), getBottleDeposit());
    }
}
