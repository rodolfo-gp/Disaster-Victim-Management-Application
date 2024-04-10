package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;

public class InquirerTest {

    @Test
    public void testGetFirstName() {
        Inquirer inquirer = new Inquirer("John", "Doe", "123-456-7890");
        assertEquals("John", inquirer.getFirstName());
    }

    @Test
    public void testGetLastName() {
        Inquirer inquirer = new Inquirer("John", "Doe", "123-456-7890");
        assertEquals("Doe", inquirer.getLastName());
    }

    @Test
    public void testGetServicesPhoneNum() {
        Inquirer inquirer = new Inquirer("John", "Doe", "123-456-7890");
        assertEquals("123-456-7890", inquirer.getServicesPhoneNum());
    }
}
