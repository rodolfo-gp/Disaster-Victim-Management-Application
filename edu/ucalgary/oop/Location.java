package edu.ucalgary.oop;
import java.util.*;

/**
 * Represents various locations capable of accommodating disaster victims.
 * 
 * This class stores information about a location, including its name, address, occupants, and supplies.
 * It provides methods to access and modify the occupants and supplies of the location.
 * 
 * @author Rodolfo Gil-Pereira
 */
public class Location {
    private String name;
    private String address;
    private Set<DisasterVictim> occupants = new HashSet<DisasterVictim>(); //list of all victims in location
    private Set<Supply> supplies = new HashSet<Supply>(); //list of all available supplies at location 

    /**
     * Constructs a new Location object with the given name and address.
     * 
     * @param name    The name of the location.
     * @param address The address of the location.
     */
    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Gets the name of the location.
     * 
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the location.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the location.
     * 
     * @return The address of the location.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the location.
     * 
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the occupants of the location.
     * 
     * @return A copy of the set of occupants.
     */
    public Set<DisasterVictim> getOccupants() {
        return new HashSet<>(occupants); // Return a copy to maintain encapsulation
    }

    /**
     * Sets the occupants of the location.
     * 
     * @param occupants The set of occupants to set.
     */
    public void setOccupants(Set<DisasterVictim> occupants) {
        this.occupants = new HashSet<>(occupants); // Clear and addAll in one step, maintains encapsulation
    }

    /**
     * Gets the supplies available at the location.
     * 
     * @return A copy of the set of supplies.
     */
    public Set<Supply> getSupplies() {
        return new HashSet<>(supplies); // Return a copy to maintain encapsulation
    }

    /**
     * Sets the supplies available at the location.
     * 
     * @param supplies The set of supplies to set.
     */
    public void setSupplies(Set<Supply> supplies) {
        this.supplies = new HashSet<>(supplies); // Clear and addAll in one step, maintains encapsulation
    }

    /**
     * Adds an occupant to the location.
     * 
     * @param occupant The occupant to add.
     */
    public void addOccupant(DisasterVictim occupant) {
        occupants.add(occupant);
    }

    /**
     * Removes an occupant from the location.
     * 
     * @param occupant The occupant to remove.
     */
    public void removeOccupant(DisasterVictim occupant) {
        occupants.remove(occupant);
    }

    /**
     * Adds or updates a supply in the supplies HashSet.
     * If a supply with the same name exists, its quantity is increased by the quantity of the supplied parameter.
     * If not, the supply is added to the set.
     *
     * @param supply The supply to add or update.
     */
    public void addSupply(Supply supply) {
        boolean found = false;
        for (Supply existingSupply : supplies) {
            if (existingSupply.getItemName().equals(supply.getItemName())) {
                // Found a supply with the same name, increase quantity
                existingSupply.setQuantity(existingSupply.getQuantity() + supply.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            // Supply not found, add it to the set
            supplies.add(supply);
        }
    }

    /**
     * Removes the specified supply from the supplies HashSet.
     * Throws an IllegalArgumentException if the updated quantity of the supply would be less than zero.
     *
     * @param supply The supply to remove.
     * @throws IllegalArgumentException If the updated quantity is less than zero.
     */
    public void removeSupply(Supply supply) {
        Iterator<Supply> iterator = supplies.iterator();
        while (iterator.hasNext()) {
            Supply existingSupply = iterator.next();
            if (existingSupply.getItemName().equals(supply.getItemName())) {
                int updatedQuantity = existingSupply.getQuantity() - supply.getQuantity();
                if (updatedQuantity < 0) {
                    throw new IllegalArgumentException("Cannot reduce quantity below zero.");
                } else if (updatedQuantity == 0) {
                    iterator.remove(); // Remove the supply if the updated quantity is zero
                } else {
                    existingSupply.setQuantity(updatedQuantity); // Update the quantity
                }
                break;
            }
        }
    }
    /**
     * Removes the specified supply from the supplies HashSet and gives it to the specified victim.
     *
     * @param supply The supply to give to the victim.
     * @param victim The victim to whom the supply will be given.
     * @throws IllegalArgumentException If the updated quantity of the supply becomes negative.
     */
    public void giveSupplyToVictim(Supply supply, DisasterVictim victim) throws IllegalArgumentException {
        // Remove the supply from the location's supplies list
        if (!supplies.contains(supply)) {
            throw new IllegalArgumentException("Supply not found at this location.");
        }
        supplies.remove(supply);

        // Find the victim in the occupants list and give the supply to them
        for (DisasterVictim occupant : occupants) {
            if (occupant.getAssignedSocialID() == victim.getAssignedSocialID()) {
                occupant.addPersonalBelonging(supply);
                return; // Exit loop once the victim is found and the supply is given
            }
        }

        // If the victim is not found in the occupants list
        throw new IllegalArgumentException("Victim not found at this location.");
    }
    /**
     * Prints information about the occupants of the location.
     */
    public void printOccupantsInfo() {
        System.out.println("Occupants of " + name + ":");
        for (DisasterVictim occupant : occupants) {
            System.out.println("ID: " + occupant.getAssignedSocialID() +
                               ", Name: " + occupant.getFirstName() +
                               " " + occupant.getLastName());
        }
    }
    /**
     * Search for an occupant with the specified ID and print its data.
     * If the occupant is found, print its ID, first name, and last name.
     * If not found, print a message indicating that the occupant with the specified ID was not found.
     * 
     * @param victimId The ID of the occupant to search for.
     */
    public void printOccupantDataById(int victimId) {
        boolean found = false;
        for (DisasterVictim occupant : occupants) {
            if (occupant.getAssignedSocialID() == victimId) {
                found = true;
                occupant.printVictimInfo();
                // You can print other data of the occupant here
                break; // Exit the loop once the occupant is found
            }
        }
        if (!found) {
            System.out.println("Occupant with ID " + victimId + " not found.");
        }
    }
}
