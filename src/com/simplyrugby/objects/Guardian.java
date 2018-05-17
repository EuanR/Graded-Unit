package com.simplyrugby.objects;

/**
 * Guardian class
 *
 * @author Euan
 */
public class Guardian {

    /**
     * Name of the junior players guardian
     */
    private String guardianName;
    /**
     * Relationship to the player
     */
    private String relationship;
    /**
     * The guardians address
     */
    private String address;
    /**
     * The guardians phone number
     */
    private String telephone;

    /**
     * Constructor
     *
     * @param guardianName Name of the junior players guardian
     * @param relationship Relationship to the player
     * @param address      The guardians address
     * @param telephone    The guardians phone number
     */
    public Guardian(String guardianName, String relationship, String address, String telephone) {
        this.guardianName = guardianName;
        this.relationship = relationship;
        this.address = address;
        this.telephone = telephone;
    }

}
