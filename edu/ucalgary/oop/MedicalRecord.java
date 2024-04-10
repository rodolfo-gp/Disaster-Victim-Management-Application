package edu.ucalgary.oop;
/**
 * Represents medical records associated with treatments received by disaster victims.
 * 
 * This class stores information about a medical treatment, including the condition name, treatment details,
 * and date of treatment.
 * 
 * @author Rodolfo Gil-Pereira
 */
public class MedicalRecord {
    private String conditionName;
    private String treatmentDetails;
    private String dateOfTreatment;

    /**
     * Constructs a new MedicalRecord object.
     * 
     * @param conditionName    The name of the medical condition being treated.
     * @param treatmentDetails Details of the treatment.
     * @param dateOfTreatment  The date of the treatment in the format YYYY-MM-DD.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public MedicalRecord(String conditionName, String treatmentDetails, String dateOfTreatment) throws IllegalArgumentException {
        setConditionName(conditionName);
        this.treatmentDetails = treatmentDetails;

        // Check if the dateOfTreatment string matches the expected date format
        if (!isValidDateFormat(dateOfTreatment)) {
            throw new IllegalArgumentException("Invalid date format for date of treatment. Expected format: YYYY-MM-DD");
        }
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * Gets the name of the medical condition being treated.
     * 
     * @return The condition name.
     */
    public String getConditionName() {
        return conditionName;
    }

    /**
     * Sets the name of the medical condition being treated.
     * 
     * @param conditionName The condition name to set.
     */
    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    /**
     * Gets the details of the treatment.
     * 
     * @return The treatment details.
     */
    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    /**
     * Sets the details of the treatment.
     * 
     * @param treatmentDetails The treatment details to set.
     */
    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    /**
     * Gets the date of the treatment.
     * 
     * @return The date of the treatment.
     */
    public String getDateOfTreatment() {
        return dateOfTreatment;
    }

    /**
     * Sets the date of the treatment.
     * 
     * @param dateOfTreatment The date of the treatment to set in the format YYYY-MM-DD.
     * @throws IllegalArgumentException If the date format is invalid.
     */
    public void setDateOfTreatment(String dateOfTreatment) {
        // Check if the dateOfTreatment string matches the expected date format
        if (!isValidDateFormat(dateOfTreatment)) {
            throw new IllegalArgumentException("Invalid date format for date of treatment. Expected format: YYYY-MM-DD");
        }
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
 * Helper method to check if a string matches the YYYY-MM-DD date format.
 * 
 * @param date The date string to check.
 * @return True if the date string is in the correct format, false otherwise.
 */
private static boolean isValidDateFormat(String date) {
    String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$"; // Regex pattern for YYYY-MM-DD format

    // Check if the date string matches the expected format
    if (!date.matches(dateFormatPattern)) {
        return false;
    }

    // Split the date string into year, month, and day parts
    String[] parts = date.split("-");
    int year = Integer.parseInt(parts[0]);
    int month = Integer.parseInt(parts[1]);
    int day = Integer.parseInt(parts[2]);

    // Check if the year, month, and day values are within valid ranges
    if (year < 0 || month < 1 || month > 12 || day < 1 || day > 31) {
        return false;
    }
    return true;
}

}
