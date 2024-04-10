package edu.ucalgary.oop;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a victim of a disaster event.
 * Each victim has personal information, medical records, family connections,
 * and personal belongings.
 * 
 * The victim's social ID is assigned automatically and cannot be changed.
 * 
 * Victims can have dietary restrictions, which are represented as an enum.
 * 
 * @author Rodolfo Gil-Pereira
 */
public class DisasterVictim {
    
    private String fName = "";
    private String lName = "";
    private int age = 0;
    private final int social_Id;
    private Set<FamilyRelation> familyRelations = new HashSet<>();
    private Set<MedicalRecord> medicalRecords = new HashSet<>();
    private Set<Supply> personalBelongings = new HashSet<>();
    private final String victimEntryDate;
    private String gender = "";
    private String notes = "";
    private String dietaryRestrictions = "";
    public static int victim_ID_Counter = 0;


    /**
     * Constructs a new DisasterVictim object with the specified first name, last name, and entry date.
     * 
     * @param fName The first name of the victim.
     * @param lName The last name of the victim.
     * @param victimEntryDate The entry date of the victim in the format YYYY-MM-DD.
     * @throws IllegalArgumentException If the entry date format is invalid.
     */
    public DisasterVictim(String fName, String lName, String victimEntryDate) {
        this.fName = fName;
        this.lName = lName;
        if (!isValidDateFormat(victimEntryDate)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.victimEntryDate = victimEntryDate;
        this.social_Id = victim_ID_Counter;
        victim_ID_Counter += 1;
    }
    
    /**
     * Checks if the given date string matches the format YYYY-MM-DD.
     * 
     * @param date The date string to check.
     * @return True if the date format is valid, false otherwise.
     */
    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        if(!date.matches(dateFormatPattern)){
            return false;
        }
        return true;
    }

    /**
     * Returns the first name of the victim.
     * 
     * @return The first name of the victim.
     */
    public String getFirstName() {
        return fName;
    }

    /**
     * Sets the first name of the victim.
     * 
     * @param fName The first name to set.
     */
    public void setFirstName(String fName) {
        this.fName = fName;
    }

    /**
     * Returns the last name of the victim.
     * 
     * @return The last name of the victim.
     */
    public String getLastName() {
        return lName;
    }

    /**
     * Sets the last name of the victim.
     * 
     * @param lName The last name to set.
     */
    public void setLastName(String lName) {
        this.lName = lName;
    }

    /**
     * Sets the age of the victim.
     * 
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the age of the victim.
     * 
     * @return The age of the victim.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Returns the social ID assigned to the victim.
     * 
     * @return The social ID of the victim.
     */
    public int getAssignedSocialID() {
        return social_Id;
    }

    /**
     * Sets the family relations of the victim.
     * 
     * @param connections The set of family relations to set.
     */
    public void setFamilyRelations(Set<FamilyRelation> connections) {
        this.familyRelations.clear();
        this.familyRelations.addAll(connections);
    }

    /**
     * Returns the family relations of the victim.
     * 
     * @return The set of family relations of the victim.
     */
    public Set<FamilyRelation> getFamilyRelations() {
        return familyRelations;
    }

    /**
     * Sets the medical records of the victim.
     * 
     * @param records The set of medical records to set.
     */
    public void setMedicalRecords(Set<MedicalRecord> records) {
        this.medicalRecords.clear();
        this.medicalRecords.addAll(records);
    }

    /**
     * Returns the medical records of the victim.
     * 
     * @return The set of medical records of the victim.
     */
    public Set<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    /**
     * Sets the personal belongings of the victim.
     * 
     * @param belongings The set of personal belongings to set.
     */
    public void setPersonalBelongings(Set<Supply> belongings) {
        this.personalBelongings = belongings;
    }

    /**
     * Returns the personal belongings of the victim.
     * 
     * @return The set of personal belongings of the victim.
     */
    public Set<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    /**
     * Adds a personal belonging to the victim's possessions.
     * If a belonging with the same name exists, the quantity is increased.
     * 
     * @param supply The supply to add to the victim's possessions.
     */
    public void addPersonalBelonging(Supply supply) {
        boolean found = false;
        for (Supply existingBelongings : personalBelongings) {
            if (existingBelongings.getItemName().equals(supply.getItemName())) {
                // Found a supply with the same name, increase quantity
                existingBelongings.setQuantity(existingBelongings.getQuantity() + supply.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            // Supply not found, add it to the set
            personalBelongings.add(supply);
        }
    }

    /**
     * Removes a personal belonging from the victim's possessions.
     * If the supply doesn't exist in personal belongings, an exception is thrown.
     * If subtracting the quantity results in a negative value, an exception is thrown.
     * 
     * @param supply The supply to remove from the victim's possessions.
     * @throws IllegalArgumentException If the supply is not found in personal belongings.
     * @throws IllegalStateException If subtracting the quantity results in a negative value.
     */
    public void removePersonalBelonging(Supply supply) {
        // Check if the supply exists in personal belongings
        boolean found = false;
        for (Supply s : personalBelongings) {
            if (s.equals(supply)) {
                found = true;
                // Update the quantity if it's greater than zero
                int newQuantity = s.getQuantity() - 1;
                if (newQuantity < 0) {
                    throw new IllegalStateException("Belongings quantity cannot be negative.");
                } else {
                    s.setQuantity(newQuantity);
                }
                break;
            }
        }
        // If the supply doesn't exist in personal belongings, throw an exception
        if (!found) {
            throw new IllegalArgumentException("Supply not found in personal belongings.");
        }
    }
        /**
     * Removes a family connection from the victim's family relations.
     * 
     * @param relation The family relation to be removed.
     */
    public void removeFamilyConnection(FamilyRelation relation) {
        familyRelations.remove(relation);
    }

    /**
     * Adds a family connection to the victim's family relations.
     * 
     * @param connection The family relation to be added.
     */
    public void addFamilyConnection(FamilyRelation connection) {  
        familyRelations.add(connection);
    }

    /**
     * Adds a medical record to the victim's medical records.
     * 
     * @param record The medical record to be added.
     */
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    /**
     * Retrieves the entry date of the victim.
     * 
     * @return The entry date of the victim.
     */
    public String getEntryDate() {
        return victimEntryDate;
    }

    /**
     * Retrieves the notes associated with the victim.
     * 
     * @return The notes associated with the victim.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes associated with the victim.
     * 
     * @param notes The notes to be set.
     */
    public void setNotes(String notes) {
        this.notes =  notes;
    }

    /**
     * Retrieves the gender of the victim.
     * 
     * @return The gender of the victim.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the victim.
     * 
     * @param gender The gender to be set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

   

    /**
     * Sets the dietary restrictions of the victim.
     * 
     * @param dietaryRestrictions The set of dietary restrictions to be set.
     */
   public void setDietaryRestrictions(String DR) {
    this.dietaryRestrictions = DR;
   }
   
   public String getDietaryRestrictions(){
        return this.dietaryRestrictions;
   }
   public void printVictimInfo() {
    System.out.println("Victim ID: " + social_Id);
    System.out.println("First Name: " + fName);
    System.out.println("Last Name: " + lName);
    System.out.println("Age: " + age);
    System.out.println("Entry Date: " + victimEntryDate);
    System.out.println("Gender: " + gender);
    System.out.println("Notes: " + notes);
    System.out.println("Dietary Restrictions: " + dietaryRestrictions);
    
    System.out.println("Family Relations:");
    for (FamilyRelation relation : familyRelations) {
        System.out.println("- " + relation.getRelation() + ": " + relation.getPerson().getFirstName() + " " + relation.getPerson().getLastName());
    }

    System.out.println("Medical Records:");
    for (MedicalRecord record : medicalRecords) {
        System.out.println("- Condition: " + record.getConditionName());
        System.out.println("  Treatment Details: " + record.getTreatmentDetails());
        System.out.println("  Date of Treatment: " + record.getDateOfTreatment());
    }

    System.out.println("Personal Belongings:");
    for (Supply supply : personalBelongings) {
        System.out.println("- " + supply.getItemName() + ": " + supply.getQuantity());
    }
}
}





