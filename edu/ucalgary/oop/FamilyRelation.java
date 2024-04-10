package edu.ucalgary.oop;
/**
 * Represents a family relation associated with a disaster victim.
 * 
 * <p>
 * FamilyRelation objects are used to store information about the relationship between a disaster victim
 * and another individual, such as a family member. Each FamilyRelation object contains details about
 * the type of relationship and the associated disaster victim.
 * </p>
 * 
 * <p>
 * This class is intended to facilitate the management of family relationships in disaster scenarios,
 * allowing for the organization and tracking of victims and their connections to other individuals.
 * </p>
 * 
 * <p>
 * FamilyRelation objects are mutable. Both the relationship type and the associated disaster victim
 * can be modified after object creation.
 * </p>
 * 
 * <p>
 * It is recommended to use this class in conjunction with the DisasterVictim class to represent
 * relationships between victims and their family members or other related individuals.
 * </p>
 * 
 * @author Rodolfo Gil Pereira
 */
public class FamilyRelation {
    private String relation;
    private DisasterVictim person;

    /**
     * Constructs a new FamilyRelation object with the specified relationship type
     * and associated disaster victim.
     * 
     * @param relation The type of relationship between the victim and another individual.
     * @param person   The disaster victim associated with the family relation.
     */
    public FamilyRelation(String relation, DisasterVictim person) {
        this.relation = relation;
        this.person = person;
    }

    /**
     * Sets the type of relationship for this family relation.
     * 
     * @param relation The type of relationship to set.
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * Retrieves the type of relationship for this family relation.
     * 
     * @return The type of relationship.
     */
    public String getRelation() {
        return this.relation;
    }

    /**
     * Sets the disaster victim associated with this family relation.
     * 
     * @param person The disaster victim to associate with this family relation.
     */
    public void setPerson(DisasterVictim person) {
        this.person = person;
    }

    /**
     * Retrieves the disaster victim associated with this family relation.
     * 
     * @return The disaster victim associated with this family relation.
     */
    public DisasterVictim getPerson() {
        return this.person;
    }
}
