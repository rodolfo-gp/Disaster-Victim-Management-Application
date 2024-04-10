package edu.ucalgary.oop;
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
  
}













