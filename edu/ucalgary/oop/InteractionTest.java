package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InteractionTest {
    private Interaction interaction;
    private String query = "Looking for family member";
    private String dateOfInquiry = "2024-02-10";

    @Before
    public void setUp() {
        interaction = new Interaction(query, dateOfInquiry);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull("Interaction object should not be null", interaction);
    }

    @Test
    public void testGetQuery() {
        assertEquals("Query should match the one set in setup", query, interaction.getQuery());
    }

    @Test
    public void testGetDateOfInquiry() {
        assertEquals("Date of inquiry should match the one set in setup", dateOfInquiry, interaction.getDateOfInquiry());
    }
}
