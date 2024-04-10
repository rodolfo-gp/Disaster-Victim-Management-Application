package edu.ucalgary.oop;
/**
 * Represents an individual who is inquiring about possible disaster victims.
 * 
 * <p>
 * An Inquirer object is immutable once created. The first name, last name, and services phone number
 * are final fields and cannot be modified after object creation.
 * </p>
 * 
 * <p>
 * Inquirers can provide additional information related to their inquiries.
 * </p>
 * 
 * <p>
 * This class is intended for non-victims who are searching for potential victims they know to help find them.
 * </p>
 * 
 * @author Rodolfo Gil
 */
public class Inquirer {
    private final String fName;
    private final String lName;
    private final String phone;

    /**
     * Constructs a new Inquirer object with the specified information.
     * 
     * @param firstName The first name of the inquirer.
     * @param lastName  The last name of the inquirer.
     * @param phone     The services phone number of the inquirer.
     * @param info      Additional information related to the inquiry.
     */
    public Inquirer(String firstName, String lastName, String phone) {
        this.fName = firstName;
        this.lName = lastName;
        this.phone = phone;
    }

    /**
     * Retrieves the first name of the inquirer.
     * 
     * @return The first name of the inquirer.
     */
    public String getFirstName() {
        return this.fName;
    }

    /**
     * Retrieves the last name of the inquirer.
     * 
     * @return The last name of the inquirer.
     */
    public String getLastName() {
        return this.lName;
    }

    /**
     * Retrieves the services phone number of the inquirer.
     * 
     * @return The services phone number of the inquirer.
     */
    public String getServicesPhoneNum() {
        return this.phone;
    }
}
