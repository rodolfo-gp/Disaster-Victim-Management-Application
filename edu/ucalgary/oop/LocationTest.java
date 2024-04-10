package edu.ucalgary.oop;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class LocationTest {
    private Location location;
    private DisasterVictim victim;
    private Supply supply;

    @Before
    public void setUp() {
        location = new Location("Location A", "1234 LocationA Ave");
        victim = new DisasterVictim("John","Doe", "2024-01-01");
        supply = new Supply("bottle", 123);
    }

    @Test
    public void testConstructor() {
        location = new Location("Location A", "1234 LocationA Ave");
        assertNotNull("Constructor should create a non-null Location object", location);
        assertEquals("Constructor should set the name correctly", "Location A", location.getName());
        assertEquals("Constructor should set the address correctly", "1234 LocationA Ave", location.getAddress());
    }

    @Test
    public void testSetName() {
        location = new Location("Location A", "1234 LocationA Ave");
        String newName = "Shelter B";
        location.setName(newName);
        assertEquals("setName should update the name of the location", newName, location.getName());
    }

    @Test
    public void testSetAddress() {
        location = new Location("Location A", "1234 LocationA Ave");
        String newAddress = "4321 Shelter Blvd";
        location.setAddress(newAddress);
        assertEquals("setAddress should update the address of the location", newAddress, location.getAddress());
    }

    @Test
    public void testAddOccupant() {
        location = new Location("Location A", "1234 LocationA Ave");
        location.addOccupant(victim);
        assertTrue("addOccupant should add a disaster victim to the occupants list", location.getOccupants().contains(victim));
    }

    @Test
    public void testRemoveOccupant() {
        location = new Location("Location A", "1234 LocationA Ave");
        location.addOccupant(victim); // Ensure the victim is added first
        location.removeOccupant(victim);
        assertFalse("removeOccupant should remove the disaster victim from the occupants list", location.getOccupants().contains(victim));
    }

    @Test
    public void testSetAndGetOccupants() {
        Set<DisasterVictim> newOccupants = new HashSet<DisasterVictim>();
        newOccupants.add(victim);
        location.setOccupants(newOccupants);
        assertTrue("setOccupants should replace the occupants hashset", location.getOccupants().containsAll(newOccupants));
    }

    @Test
    public void testAddSupply() {
        Location location = new Location("Location A", "1234 LocationA Ave");
        location.addSupply(supply);
        assertTrue("addSupply should add a supply to the supplies list", location.getSupplies().contains(supply));
    }
    
    @Test
public void testAddSupplyAlreadyPresent() {
    Location location = new Location("Location A", "1234 LocationA Ave");
    Supply supply = new Supply("Water Bottle", 10);
    location.addSupply(supply);
    location.addSupply(supply);
    assertEquals("addSupply should increase quantity when supply is already present", 20, location.getSupplies().iterator().next().getQuantity());
}


@Test
public void testRemoveSupply() {
    Location location = new Location("Location A", "1234 LocationA Ave");
    Supply supply = new Supply("Water Bottle", 10);
    location.addSupply(supply); // Ensure the supply is added first
    int initialSupplyListLength = location.getSupplies().size();
    location.removeSupply(supply);

    assertFalse("removeSupply should remove the supply from the supplies list", location.getSupplies().contains(supply));
    assertEquals("The size of the supplies list should decrease by 1 after removing a supply", initialSupplyListLength - 1, location.getSupplies().size());
}



    @Test
    public void testSetAndGetSupplies() {
        Set<Supply> newSupplies = new HashSet<Supply>(); 
        newSupplies.add(supply);
        location.setSupplies(newSupplies);
        assertFalse("setSupplies should replace the supplies list with the new list", location.getSupplies().size() == 0);
    }
}
