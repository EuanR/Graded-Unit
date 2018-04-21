package com.simplyrugby.objects;

/**
 * @author Euan
 */
public class Guardian {

    private String guardianName;
    private String relationship;
    private String address;
    private String telephone;

    public Guardian(String guardianName, String relationship, String address, String telephone) {
        this.guardianName = guardianName;
        this.relationship = relationship;
        this.address = address;
        this.telephone = telephone;
    }

}
