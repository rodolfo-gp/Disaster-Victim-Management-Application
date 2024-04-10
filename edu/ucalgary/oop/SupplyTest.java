
package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class SupplyTest {
    String expectedType = "brush";
	int expectedQuantity = 12;
    private Supply supply = new Supply(expectedType, expectedQuantity);


    @Test
    public void testObjectCreation() {
        assertNotNull(supply);
    }
	

    @Test
    public void testGetType() {
        assertEquals("getType should return the correct type", expectedType, supply.getItemName());
    }

    @Test
    public void testSetItemName() {
        supply.setItemName("Food");
        assertEquals("setType should update the type", "Food", supply.getItemName());
    }


    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity should return the correct quantity", expectedQuantity, supply.getQuantity());
    }


    @Test
    public void testSetQuantity() {
        supply.setQuantity(20);
        assertEquals("setQuantity should update the quantity", 20, supply.getQuantity());
    }
}
