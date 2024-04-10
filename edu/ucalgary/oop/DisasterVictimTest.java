package edu.ucalgary.oop;
import edu.ucalgary.oop.DisasterVictim.DietaryRestriction;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;



public class DisasterVictimTest {

    private DisasterVictim victim1, victim2, victim3;
    private String fName = "p1";
    private String lName = "p1";
    private int age = 20;
    // private final int social_Id;
    private Set<FamilyRelation> familyRelations = new HashSet<>();
    private Set<MedicalRecord> medicalRecords = new HashSet<>();
    private Set<Supply> personalBelongings = new HashSet<>();
    private String validDate = "2000-01-01";
    private String invalidDate = "2000/01/01";
    private String gender = "Man";
    private String notes = "These are victim notes...";
    private Set<DietaryRestriction> dietaryRestrictions = new HashSet<>();

    
    private FamilyRelation familyRelation;
    

    @Before
    public void setUp() {
        victim1 = new DisasterVictim("Rodolfo","Gil", "2000-01-01");
        victim2 = new DisasterVictim("Sponge", "Bob", "2000-01-02");
        victim3 = new DisasterVictim("Jonathan", "JoeStar", "2000-01-03");
        
    }

    @Test
    public void testConstructorValidDate() {
    
        DisasterVictim victim = new DisasterVictim("p1", "p1", validDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Getter Shoul return valid date: ", validDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidDate() {
        DisasterVictim vic = new DisasterVictim("p1", "p1", invalidDate);
    }

   @Test
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth, victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        String invalidDate = "41/1";
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
        String expectedComments = "is not deaf";
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }

    @Test
    public void testSetComments() {
        String expectedComments = "is not deaf";
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }

    @Test
    public void testGetAssignedSocialID() {
        DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId, actualVictim.getAssignedSocialID());
    }

   
    @Test
    public void testSetAndGetGender() {
        String newGender = "man";
        newGender.toLowerCase();
        victim.setGender(newGender);
        assertEquals("setGender should update and getGender should return the new gender", newGender.toLowerCase(), victim.getGender());
    }
    

    @Test
    public void testAddAndRemovePersonalBelonging() {
        Set<Supply> expectedSupplies =  new HashSet<>();
        Supply newSupply = new Supply("Emergency Kit", 1);
        Location testLocation = new Location("Schulich School of Engineering", "662 Collegiate Pl NW");
        testLocation.addSupply(newSupply);
        victim.addPersonalBelonging(newSupply, testLocation ,1);
        expectedSupplies.add(newSupply);
        assertEquals("addPersonalBelonging should add the supply to personal belongings", victim.getPersonalBelongings(), expectedSupplies);
        victim.removePersonalBelonging(newSupply);
        assertFalse("removePersonalBelonging should remove the supply from personal belongings", victim.getPersonalBelongings().contains(newSupply));

    }
    

    @Test
    public void testAddAndRemoveFamilyConnection() {
        Set<DisasterVictim> familyMembers = new HashSet<>();
        familyMembers.add(victim1);
        familyMembers.add(victim2);

        familyRelation = new FamilyRelation(familyMembers, "Parent");

        assertEquals("Parent", familyRelation.getFamilyRole());
        assertEquals(familyMembers, familyRelation.getFamilyMembers());

        victim.removeFamilyConnection(familyRelation);
        assertFalse("Victim should no longer have the connection", victim.getFamilyConnections().contains(familyRelation));
    }

    @Test
    public void testSetMedicalRecords() {
        Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
        Set<MedicalRecord> newRecords = new HashSet<>();
        newRecords.add(testRecord);
        victim.setMedicalRecords(newRecords);
        Set<MedicalRecord> actualRecords = victim.getMedicalRecords();
    
        assertEquals("setMedicalRecords should correctly update medical records", newRecords, actualRecords);
    }
    @Test
    public void testAddDietaryRestrictions() {
        Set<DietaryRestriction> expectedRestrictions = new HashSet<>();
        victim.addDietaryRestrictions("AVML");
        expectedRestrictions.add(DietaryRestriction.AVML);
        assertEquals("addDietaryRestrictions should correctly update dietary restrictions", victim.getDietaryRestrictions(), expectedRestrictions);

    }
    



  
  
}













