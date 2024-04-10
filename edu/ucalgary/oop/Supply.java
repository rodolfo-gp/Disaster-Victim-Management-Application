package edu.ucalgary.oop;

/**
 * @author Rodolfo Gil-Pereira
 * Represents supply items that can belong to a location or a disaster victim as personal belongings.
 */
public class Supply {
    
    private String itemName;
    private int quantity;

    /**
     * Constructs a new Supply object with the given item name and quantity.
     * @param itemName The name of the supply item.
     * @param quantity The quantity of the supply item.
     */
    public Supply(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
//////////Setters/////////////////////////
    /**
     * Sets the type (name) of the supply item.
     * @param itemName The name of the supply item.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Sets the quantity of the supply item.
     * @param quantity The quantity of the supply item.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
//////////getters///////////////////////////////////
    /**
     * Gets the type (name) of the supply item.
     * @return The name of the supply item.
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Gets the quantity of the supply item.
     * @return The quantity of the supply item.
     */
    public int getQuantity() {
        return this.quantity;
    }
/////////////////other methods////////////////////

    /**
     * Reduces the quantity of the supply item by the specified amount.
     * @param taken The amount to reduce the quantity by.
     * @throws IllegalArgumentException if the specified amount is less than 0 or if reducing the quantity would result in a negative quantity.
     */
    public void removeQuantity(int amount) {
        if (this.quantity - amount < 0) {
            throw new IllegalArgumentException("cannot reduce to negative quantity");
        }
        this.quantity -= amount;
    }
}
