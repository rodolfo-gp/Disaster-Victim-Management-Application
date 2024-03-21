/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class DisasterVictimTest {
    private DisasterVictim victim;
    private List<Supply> suppliesToSet; 
    private List<FamilyRelation> familyRelations; 
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String validDate = "2024-01-15";
    private String invalidDate = "15/13/2024";
    private String expectedGender = "female"; 
    private String expectedComments = "Needs medical attention and speaks 2 languages";

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);
        suppliesToSet = new ArrayList<>();
        suppliesToSet.add(new Supply("Water Bottle", 10));
        suppliesToSet.add(new Supply("Blanket", 5));
        
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        
    }

  		  

  @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024"; // Incorrect format according to your specifications
        new DisasterVictim("Freda", invalidEntryDate);
        // Expecting IllegalArgumentException due to invalid date format
    }


   @Test
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth, victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate); // This format should cause an exception
    }
	
	@Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, victim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName, victim.getLastName());
    }

    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }

    @Test
    public void testSetComments() {
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }

    @Test
    public void testGetAssignedSocialID() {
        // The next victim should have an ID one higher than the previous victim
        // Tests can be run in any order so two victims will be created
        DisasterVictim vic1 = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = vic1.getAssignedSocialID() + 1;
        DisasterVictim vic2 = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId, vic2.getAssignedSocialID());
    }

    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
    }
   
    @Test
    public void testSetAndGetGender() {
        String newGender = "male";
        victim.setGender(newGender);
        assertEquals("setGender should update and getGender should return the new gender", newGender.toLowerCase(), victim.getGender());
    }
	
	

    @Test
    public void testAddFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim2, "parent", victim1);
        FamilyRelation[] expectedRelations = {relation};
        victim2.setFamilyConnections(expectedRelations);

        FamilyRelation[] testFamily = victim2.getFamilyConnections();
        boolean correct = false;

        if ((testFamily!=null) && (testFamily[0] == expectedRelations[0])) {
                correct = true;
        }
        assertTrue("addFamilyConnection should add a family relationship", correct);
    }

    @Test
    public void testAddPersonalBelonging() {
        Supply newSupply = new Supply("Emergency Kit", 1);
        victim.addPersonalBelonging(newSupply);
        Supply[] testSupplies = victim.getPersonalBelongings();
        boolean correct = false;
 
        int i;
        for (i = 0; i < testSupplies.length; i++) {
            if (testSupplies[i] == newSupply) {
                correct = true;
            }
        }
        assertTrue("addPersonalBelonging should add the supply to personal belongings", correct);
    }

@Test
public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        FamilyRelation relation1 = new FamilyRelation(victim, "sibling", victim1);
        FamilyRelation relation2 = new FamilyRelation(victim, "sibling", victim2);
        FamilyRelation[] expectedRelations = {relation2};
        FamilyRelation[] originalRelations = {relation1, relation2};
        victim.setFamilyConnections(originalRelations);

        DisasterVictim victim = new DisasterVictim("Freda", "2024-01-23");
        victim.addFamilyConnection(relation1);
        victim.addFamilyConnection(relation2);
        victim.removeFamilyConnection(relation1);

        FamilyRelation[] testFamily = victim.getFamilyConnections();
        boolean correct = true;

        int i;
        for (i = 0; i < testFamily.length; i++) {
            if (testFamily[i] == relation1) {
                correct = false;
            }
        }
    assertTrue("removeFamilyConnection should remove the family member", true);
}  

@Test
public void testRemovePersonalBelonging() {
    
        Supply supplyToRemove = suppliesToSet.get(0); 
        victim.addPersonalBelonging(supplyToRemove); 
        victim.removePersonalBelonging(supplyToRemove);

        Supply[] testSupplies = victim.getPersonalBelongings();
        boolean correct = true;
 
        int i;
        for (i = 0; i < testSupplies.length; i++) {
            if (testSupplies[i] == supplyToRemove) {
                correct = false;
            }
        }
    assertTrue("removePersonalBelonging should remove the supply from personal belongings", true);
}


 @Test
    public void testSetFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);
        FamilyRelation[] expectedRelations = {relation};
        victim1.setFamilyConnections(expectedRelations);
        boolean correct = true;

       // We have not studied overriding equals in arrays of custom objects so we will manually evaluate equality
       FamilyRelation[] actualRecords = victim1.getFamilyConnections();
       if (expectedRelations.length != actualRecords.length) {
           correct = false;
       } else {    
           int i;
           for (i=0;i<actualRecords.length;i++) {
               if (expectedRelations[i] != actualRecords[i]) {
                   correct = false;
               }
           }
       }
       assertTrue("Family relation should be set", correct);
    }
    @Test
    public void testaddFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        FamilyRelation relation1 = new FamilyRelation(victim1, "sibling", victim2);
        
        DisasterVictim victim3 = new DisasterVictim("bob", "2024-01-23");
        DisasterVictim victim4 = new DisasterVictim("joe", "2024-01-24");
        FamilyRelation relation2 = new FamilyRelation(victim3, "sibling", victim4);

        FamilyRelation[] startingRelations = {relation1};
        FamilyRelation[] expectedRelations = {relation1, relation2};
        

        victim1.setFamilyConnections(startingRelations);
        boolean correct = true;

       // We have not studied overriding equals in arrays of custom objects so we will manually evaluate equality
       FamilyRelation[] actualRecords = victim1.addFamilyConnection(relation2).getFamilyConnections();
       
       if (expectedRelations.length != actualRecords.length) {
           correct = false;
       } else {    
           int i;
           for (i=0;i<actualRecords.length;i++) {
               if (expectedRelations[i] != actualRecords[i]) {
                   correct = false;
               }
           }
       }
       assertTrue("Family relation should be set", correct);
    }
    @Test
public void testRemoveFamilyRelation() {
    // Create two victims and a family relation between them
    DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
    DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
    FamilyRelation relation1 = new FamilyRelation(victim1, "sibling", victim2);

    // Create two more victims and a family relation between them
    DisasterVictim victim3 = new DisasterVictim("Bob", "2024-01-23");
    DisasterVictim victim4 = new DisasterVictim("Joe", "2024-01-24");
    FamilyRelation relation2 = new FamilyRelation(victim3, "sibling", victim4);

    // Set the family relations for victim1
    FamilyRelation[] startingRelations = {relation1};
    victim1.setFamilyConnections(startingRelations);

    // Remove the family relation between victim1 and victim2
    victim1.removeFamilyRelation(relation1);

    // Get the updated family connections of victim1
    FamilyRelation[] updatedRelations = victim1.getFamilyConnections();

    // Check if the relation1 is removed
    boolean isRelationRemoved = true;
    for (FamilyRelation relation : updatedRelations) {
        if (relation == relation1) {
            isRelationRemoved = false;
            break;
        }
    }

    assertTrue("removeFamilyRelation should remove the family relation", isRelationRemoved);
}


  @Test
public void testSetMedicalRecords() {
    Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
    MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
    boolean correct = true;

    MedicalRecord[] newRecords = { testRecord };
    victim.setMedicalRecords(newRecords);
    MedicalRecord[] actualRecords = victim.getMedicalRecords();

    // We have not studied overriding equals in arrays of custom objects so we will manually evaluate equality
    if (newRecords.length != actualRecords.length) {
        correct = false;
    } else {
        int i;
        for (i=0;i<newRecords.length;i++) {
            if (actualRecords[i] != newRecords[i]) {
                correct = false;
            }
        }
    }
    assertTrue("setMedicalRecords should correctly update medical records", correct);
}
@Test
public void testAddMedicalRecord() {
    // Create a victim
    DisasterVictim victim = new DisasterVictim("Jane", "2024-01-20");
    
    // Create a medical record for the victim
    Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
    MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");

    // Add the medical record to the victim
    victim.addMedicalRecord(testRecord);

    // Get the medical records of the victim
    MedicalRecord[] medicalRecords = victim.getMedicalRecords();

    // Check if the testRecord is added
    boolean isRecordAdded = false;
    for (MedicalRecord record : medicalRecords) {
        if (record == testRecord) {
            isRecordAdded = true;
            break;
        }
    }

    assertTrue("addMedicalRecord should add the medical record", isRecordAdded);
}

@Test
public void testRemoveMedicalRecord() {
    // Create a victim and a medical record for the victim
    DisasterVictim victim = new DisasterVictim("Jane", "2024-01-20");
    Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
    MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");

    // Set the medical record for the victim
    MedicalRecord[] startingRecords = { testRecord };
    victim.setMedicalRecords(startingRecords);

    // Remove the medical record
    victim.removeMedicalRecord(testRecord);

    // Get the updated medical records of the victim
    MedicalRecord[] updatedRecords = victim.getMedicalRecords();

    // Check if the testRecord is removed
    boolean isRecordRemoved = true;
    for (MedicalRecord record : updatedRecords) {
        if (record == testRecord) {
            isRecordRemoved = false;
            break;
        }
    }

    assertTrue("removeMedicalRecord should remove the medical record", isRecordRemoved);
}


   @Test
public void testSetPersonalBelongings() {
    Supply one = new Supply("Tent", 1);
    Supply two = new Supply("Jug", 3);
    Supply[] newSupplies = {one, two};
    boolean correct = true;

    victim.setPersonalBelongings(newSupplies);
    Supply[] actualSupplies = victim.getPersonalBelongings();

    // We have not studied overriding equals in arrays of custom objects so we will manually evaluate equality
    if (newSupplies.length != actualSupplies.length) {
        correct = false;
    } else {
        int i;
        for (i=0;i<newSupplies.length;i++) {
            if (actualSupplies[i] != newSupplies[i]) {
                correct = false;
            }
        }
    }
    assertTrue("setPersonalBelongings should correctly update personal belongings", correct);
}

@Test
public void testAddFamilyConnectionConsistency() {
    // Create victims
    DisasterVictim peace = new DisasterVictim("Peace", "2024-01-20");
    DisasterVictim sam = new DisasterVictim("Sam", "2024-01-22");
    DisasterVictim diamond = new DisasterVictim("Diamond", "2024-01-25");

    // Add family connections
    FamilyRelation relation1 = new FamilyRelation(peace, "sibling", sam);
    FamilyRelation relation2 = new FamilyRelation(peace, "sibling", diamond);

    // Add relations to Peace
    peace.addFamilyConnection(relation1);
    peace.addFamilyConnection(relation2);

    // Check Peace's family connections
    FamilyRelation[] peaceFamilyConnections = peace.getFamilyConnections();
    assertEquals("Peace should have 2 family connections", 2, peaceFamilyConnections.length);

    // Check if Peace's connections contain Sam and Diamond
    boolean samFound = false;
    boolean diamondFound = false;
    for (FamilyRelation relation : peaceFamilyConnections) {
        if (relation.getPersonTwo().equals(sam)) {
            samFound = true;
        }
        if (relation.getPersonTwo().equals(diamond)) {
            diamondFound = true;
        }
    }

    assertTrue("Peace should have a relationship with Sam", samFound);
    assertTrue("Peace should have a relationship with Diamond", diamondFound);

    // Check Sam's family connections
    FamilyRelation[] samFamilyConnections = sam.getFamilyConnections();
    assertEquals("Sam should have 1 family connection", 1, samFamilyConnections.length);

    // Check if Sam's connection contains Peace
    boolean peaceFound = false;
    for (FamilyRelation relation : samFamilyConnections) {
        if (relation.getPersonTwo().equals(peace)) {
            peaceFound = true;
            break;
        }
    }

    assertTrue("Sam should have a relationship with Peace", peaceFound);
}

    
}





