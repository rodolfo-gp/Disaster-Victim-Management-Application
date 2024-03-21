/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class LocationTest {
    private Location location;
    private DisasterVictim victim;
    private Supply supply;

    @Before
    public void setUp() {
        // Initializing test objects before each test method
        location = new Location("Shelter A", "1234 Shelter Ave");
        victim = new DisasterVictim("John Doe", "2024-01-01");
        supply = new Supply("Water Bottle", 10);
    }

    // Helper method to check if a supply is in the list
    private boolean containsSupply(ArrayList<Supply> supplies, Supply supplyToCheck) {
        for (Supply supply : supplies) {
            if (supply.getType().equalsIgnoreCase(supplyToCheck.getType()) && supply.getQuantity() >= 1) {
                return true;
            }
        }
        return false;
    }
    

    @Test
    public void testConstructor() {
        location = new Location("Shelter A", "1234 Shelter Ave");
        assertNotNull("Constructor should create a non-null Location object", location);
        assertEquals("Constructor should set the name correctly", "Shelter A", location.getName());
        assertEquals("Constructor should set the address correctly", "1234 Shelter Ave", location.getAddress());
    }

    @Test
    public void testSetName() {
        location = new Location("Shelter A", "1234 Shelter Ave");
        String newName = "Shelter B";
        location.setName(newName);
        assertEquals("setName should update the name of the location", newName, location.getName());
    }

    @Test
    public void testSetAddress() {
        location = new Location("Shelter A", "1234 Shelter Ave");
        String newAddress = "4321 Shelter Blvd";
        location.setAddress(newAddress);
        assertEquals("setAddress should update the address of the location", newAddress, location.getAddress());
    }

    @Test
    public void testAddOccupant() {
        location = new Location("Shelter A", "1234 Shelter Ave");
        location.addOccupant(victim);
        assertTrue("addOccupant should add a disaster victim to the occupants list", location.getOccupants().contains(victim));
    }

    @Test
    public void testRemoveOccupant() {
        location = new Location("Shelter A", "1234 Shelter Ave");
        location.addOccupant(victim); // Ensure the victim is added first
        location.removeOccupant(victim);
        assertFalse("removeOccupant should remove the disaster victim from the occupants list", location.getOccupants().contains(victim));
    }

    @Test
    public void testSetAndGetOccupants() {
        ArrayList<DisasterVictim> newOccupants = new ArrayList<>();
        newOccupants.add(victim);
        location.setOccupants(newOccupants);
        assertTrue("setOccupants should replace the occupants list with the new list", location.getOccupants().containsAll(newOccupants));
    }

    @Test
    public void testAddSupply() {
        location = new Location("Shelter A", "1234 Shelter Ave");
        location.addSupply(supply);
        assertTrue("addSupply should add a supply to the supplies list", containsSupply(location.getSupplies(), supply));
    }
    @Test
    public void testAddSupplyAlreadyPresent() {
        location = new Location("Shelter A", "1234 Shelter Ave");
        supply = new Supply("Water Bottle", 10);
        location.addSupply(supply);
        location.addSupply(supply);
        assertTrue("addSupply should add a supply to the supplies list", location.getSupplies().get(0).getQuantity() == 20);
    }

    @Test
    public void testRemoveSupply() {

        supply = new Supply("Water Bottle", 10);
        location.addSupply(supply); // Ensure the supply is added first
        int initialQuantity = supply.getQuantity();
        location.removeSupply(supply);

        if (initialQuantity > 1) {
            // Check that the quantity decreases but the supply is still present
            assertTrue("removeSupply should decrease the quantity of the supply", supply.getQuantity() < initialQuantity);
            assertTrue("removeSupply should still keep the supply in the supplies list", containsSupply(location.getSupplies(), supply));
        } else {
            // Check that the supply is completely removed if its quantity is one
            assertFalse("removeSupply should remove the supply from the supplies list", containsSupply(location.getSupplies(), supply));
        }
    }


    @Test
    public void testSetAndGetSupplies() {
        ArrayList<Supply> newSupplies = new ArrayList<>();
        newSupplies.add(supply);
        location.setSupplies(newSupplies);
        assertTrue("setSupplies should replace the supplies list with the new list", containsSupply(location.getSupplies(), supply));
    }
}
