package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles how inquirers' requests are handled.
 * 
 * @author Rodolfo Gil-Pereira
 */
public class ReliefService {
    private Inquirer inquirer;
    private DisasterVictim victim;
    private String dateOfInquiry;
    private String info;
    private Location lastKnownLocation;

    /**
     * Constructs a new ReliefService object.
     * 
     * @param inquirer         The inquirer making the request.
     * @param victim           The missing person for whom the request is made.
     * @param dateOfInquiry    The date of the inquiry in the format YYYY-MM-DD.
     * @param info             Additional information provided with the inquiry.
     * @param lastKnownLocation The last known location of the missing person.
     */
    public ReliefService(Inquirer inquirer, DisasterVictim victim, String dateOfInquiry, String info, Location lastKnownLocation) {
        this.inquirer = inquirer;
        this.victim = victim;
        this.dateOfInquiry = dateOfInquiry;
        this.info = info;
        this.lastKnownLocation = lastKnownLocation;
    }

    /**
     * Gets the inquirer.
     * 
     * @return The inquirer.
     */
    public Inquirer getInquirer() {
        return inquirer;
    }

    /**
     * Sets the inquirer.
     * 
     * @param inquirer The inquirer to set.
     */
    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    /**
     * Gets the missing person.
     * 
     * @return The missing person.
     */
    public DisasterVictim getVictim() {
        return victim;
    }

    /**
     * Sets the missing person.
     * 
     * @param victim The missing person to set.
     */
    public void setVictim(DisasterVictim victim) {
        this.victim = victim;
    }

    /**
     * Gets the date of the inquiry.
     * 
     * @return The date of the inquiry.
     */
    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    /**
     * Sets the date of the inquiry.
     * 
     * @param dateOfInquiry The date of the inquiry to set in the format YYYY-MM-DD.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public void setDateOfInquiry(String dateOfInquiry) {
        // Check if the dateOfInquiry string matches the expected date format
        if (!isValidDateFormat(dateOfInquiry)) {
            throw new IllegalArgumentException("Invalid date format for date of inquiry. Expected format: YYYY-MM-DD");
        }
        this.dateOfInquiry = dateOfInquiry;
    }

    /**
     * Gets the additional information provided with the inquiry.
     * 
     * @return The additional information provided.
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the additional information provided with the inquiry.
     * 
     * @param info The additional information provided to set.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Gets the last known location of the missing person.
     * 
     * @return The last known location.
     */
    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    /**
     * Sets the last known location of the missing person.
     * 
     * @param lastKnownLocation The last known location to set.
     */
    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    /**
     * Checks if a string matches the YYYY-MM-DD date format.
     * 
     * @param date The date string to check.
     * @return True if the date string is in the correct format, false otherwise.
     */
    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets details of the inquiry log.
     * 
     * @return Details of the inquiry log.
     */
    public String getLogDetails() {
        return "Inquirer: " + inquirer.getFirstName() + ", "+ inquirer.getLastName()+
               ", Missing Person: " + victim.getFirstName() + 
               ", Date of Inquiry: " + dateOfInquiry + 
               ", Info Provided: " + info + 
               ", Last Known Location: " + lastKnownLocation.getName();
    }
}
