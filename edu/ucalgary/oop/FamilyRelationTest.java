package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    private DisasterVictim personTwo = new DisasterVictim("John", "Dalan", "2024-02-20");
    private String relationshipTo = "sibling";
    private FamilyRelation testFamilyRelationObject = new FamilyRelation(relationshipTo, personTwo);
    
    @Test
    public void testObjectCreation() {
        assertNotNull(testFamilyRelationObject);
    }
	
    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New", "Person", "2024-03-21");
        testFamilyRelationObject.setPerson(newPersonOne);
        assertEquals("setPersonOne should update personOne", newPersonOne, testFamilyRelationObject.getPerson());
    }


    @Test
    public void testSetAndGetRelationshipTo() {
        String newRelationship = "parent";
        testFamilyRelationObject.setRelation(newRelationship);
        assertEquals("setRelationshipTo should update the relationship", newRelationship, testFamilyRelationObject.getRelation());
    }

//     @Test
//     public void testModifyAssociatedFamilyRelation() {
//     // Create victims
//     DisasterVictim peace = new DisasterVictim("Peace", "t1", "2024-01-20");
//     DisasterVictim sam = new DisasterVictim("Sam","t2" , "2024-01-22");

//     // Add initial family connection
//     FamilyRelation relation1 = new FamilyRelation("sibling", sam);
//     peace.addFamilyConnection(relation1);

//     // Create a new relation with the same people but different relation type
//     FamilyRelation newRelation = new FamilyRelation("cousin", sam);

//     // Modify associated family relation
//     peace.ModifyAssociatedFamilyRelation(newRelation);

//     // Check if the new relation is added to both sides
//     FamilyRelation[] peaceFamilyConnections = peace.getFamilyConnections();
//     FamilyRelation[] samFamilyConnections = sam.getFamilyConnections();

//     // Check if both sides have the relationship
//     boolean peaceHasRelation = false;
//     boolean samHasRelation = false;
//     for (FamilyRelation relation : peaceFamilyConnections) {
//         if (relation.equals(newRelation)) {
//             peaceHasRelation = true;
//             break;
//         }
//     }

//     for (FamilyRelation relation : samFamilyConnections) {
//         if (relation.equals(newRelation)) {
//             samHasRelation = true;
//             break;
//         }
//     }

//     assertTrue("Peace should have the modified family relation", peaceHasRelation);
//     assertTrue("Sam should have the modified family relation", samHasRelation);
// }

}
