/*
Copyright Ann Barcomb and Khawla Shnaikat, 2024
Licensed under GPL v3
See LICENSE.txt for more information.
*/
package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class InquirerTest {

    private String expectedFirstName = "Joseph";
    private String expectedLastName = "Bouillon";
    private String expectedPhoneNumber = "+1-123-456-7890";
    private String expectedMessage = "looking for my family members";
    private ArrayList<Interaction> startingInteractions = new ArrayList<>();
    private Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);

    @Before
    public void setUp() {
        inquirer = new Inquirer("Joseph", "Bouillon", "+1-123-456-7890", "looking for my family members");
    }

    
/* 
testObjectCreation -> means testing Inquirer constructor: 
   - What we need: To verify that an "Inquirer" object is successfully created.
   - Actual result: The name "Joseph Bouillon", the services phone number "+1-123-456-7890", and the provided info is "looking for my family members".
   - Expected Result: The test checks that the Inquirer object is not null, confirming successful object creation.
*/
    @Test
    public void testObjectCreation() {
        assertNotNull(inquirer);
    }

/*
testGetFirstName**: 
   - What we need: To ensure the "getFirstName()" method correctly returns the actual inquirer's first name.
   - Actual result: "Joseph".
   - Expected result: "inquirer.getFirstName()" should return "Joseph".
   */
    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName() should return inquirer's first name", expectedFirstName, inquirer.getFirstName());
    }
	
/*
testGetLastName: 
   - What we need: To confirm that the "getLastName()" method accurately returns inquirer's last name.
   - Actual result:"Bouillon".
   - Expected result: "inquirer.getLastName()" should return "Bouillon".
*/
    @Test
    public void testGetLastName() {
        assertEquals("getLastName() should return inquirer's last name", expectedLastName, inquirer.getLastName());
    }
	
/*
testGetServicesPhoneNum**: 
   - What we need: To confirm that "getServicesPhoneNum()" method correctly returns the services phone number.
   - Actual result: "+1-123-456-7890".
   - Expected result: "inquirer.getServicesPhoneNum()" should return "+1-123-456-7890".
*/
    @Test
    public void testGetServicesPhoneNum() {

        assertEquals("getServicesPhoneNum() should return the correct Services Number",expectedPhoneNumber, inquirer.getServicesPhoneNum());
    }
	
/*
testGetInfo:
   - What we need: To confirm that "getInfo()" method retrieves the correct information string. 
   - Actual result: "looking for my family members".
   - Expected result: "inquirer.getInfo()" should return the string "looking for my family members".
*/
    @Test
    public void testGetInfo() {
        assertEquals("getInfo() should return the inquirer message", expectedMessage,inquirer.getInfo());
    }
    @Test
    public void testSetFirstName() {
        String newFirstName = "John";
        inquirer.setFirstName(newFirstName);
        assertEquals("setFirstName() should update the first name", newFirstName, inquirer.getFirstName());
    }

    @Test
    public void testSetLastName() {
        String newLastName = "Doe";
        inquirer.setLastName(newLastName);
        assertEquals("setLastName() should update the last name", newLastName, inquirer.getLastName());
    }

    @Test
    public void testSetInfo() {
        String newInfo = "seeking assistance";
        inquirer.setInfo(newInfo);
        assertEquals("setInfo() should update the information", newInfo, inquirer.getInfo());
    }

    @Test
    public void testSetServicesPhone() {
        // Valid phone format
        String newPhone = "+1-987-654-3210";
        inquirer.setServicesPhone(newPhone);
        assertEquals("setServicesPhone() should update the services phone number", newPhone, inquirer.getServicesPhone());

        // Invalid phone format
        String invalidPhone = "9876543210"; // Missing country code and dashes
        try {
            inquirer.setServicesPhone(invalidPhone);
            fail("Invalid phone format should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Pass the test
        }
    }

    @Test
    public void testLogInteraction() {
        Interaction interaction = new Interaction("searchPerson=John", "2024-01-15");
        inquirer.logInteraction(interaction);
        
        ArrayList<Interaction> resultInteractions = inquirer.getInteractions();
        if(startingInteractions.length >= resultInteractions.length){
            fail("Interaction List lenghth mis-match")
        }
        
        assertTrue("logInteraction() should add the interaction to the list of interactions", interactions.contains(interaction));
    }
}

