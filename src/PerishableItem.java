
/******************************************************************************
 * Course:      CS300 - Summer 2026
 * Program:     Program: Digital Library App
 * Author:        Jacob bolling
 * Wisc Email:  jbolling@wisc.edu
 * Created on:  6/22/2026
 * Version:     1.0
 *              Perishable Item subclass which tracks when an item expires, and when it is due to be marked down for clearance

 * @see Item

 *******************************************************************************/
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class PerishableItem extends Item {
    private LocalDate expirationDate;
    public PerishableItem(int id, String name, double price, int quantity, LocalDate expiration) {
        super(id, name, price, quantity);
        setExpirationDate(expiration);
    }
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
    public boolean isExpiringSoon() {
        long timeBetween = ChronoUnit.DAYS.between(LocalDate.now(), this.expirationDate);
        return timeBetween >= 0 && timeBetween <= 2;
    }
    public boolean isExpired() {
        long timeBetween = ChronoUnit.DAYS.between(LocalDate.now(), this.expirationDate);
        return timeBetween < 0;
    }
    @Override
    public double calculateFinalPrice() {
        if (this.isExpiringSoon()) {
            return getPrice() * getQuantity();
        } else {
            return getPrice() * ((double) getQuantity()) * 0.5;
        }
    }
    @Override
    public boolean canAdd(int qty) {
        /*if (isExpired()) {
            return false;
        }
        if (this.getQuantity() < qty) {
            return false;
        }
        return true;
        */
        // simplified version of above
        return !(isExpired() || this.getQuantity() < qty);
    }
    @Override
    public String toFileString() {
        return "P," + super.toFileString() + "," + expirationDate.toString();
    }

}
