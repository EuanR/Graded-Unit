package com.simplyrugby.objects;

import java.util.ArrayList;

/**
 * Senior player class
 *
 * @author Euan
 */
public class SeniorPlayer extends Player {

    /**
     * Players next of kin
     */
    String nextOfKin = "";
    /**
     * Next of kin telephone number
     */
    String nextOfKinTelephone = "";

    /**
     * Constructor
     *
     * @param UID                Members ID
     * @param firstname          Members first name
     * @param surname            Members surname
     * @param address            Members address
     * @param postcode           Members postcode
     * @param SRUNumber          SRU number of the member
     * @param dateOfBirth        Members date of birth
     * @param telephoneNumber    Members phone number
     * @param mobileNumber       Members mobile number
     * @param email              Members email
     * @param password           Members password
     * @param doctor             Members doctor
     * @param doctorTelephone    Doctors telephone number
     * @param position           Players position
     * @param healthIssues       List of any health issues
     * @param nextOfKin          Players next of kin
     * @param nextOfKinTelephone Next of kin telephone number
     */
    public SeniorPlayer(int UID, String firstname, String surname, String address, String postcode, String SRUNumber, String dateOfBirth, String telephoneNumber, String mobileNumber, String email, String password, String doctor, String doctorTelephone, String position, ArrayList<String> healthIssues, String nextOfKin, String nextOfKinTelephone) {
        super(UID, firstname, surname, address, postcode, SRUNumber, dateOfBirth, telephoneNumber, mobileNumber, email, password, doctor, doctorTelephone, position, healthIssues);
        this.nextOfKin = nextOfKin;
        this.nextOfKinTelephone = nextOfKinTelephone;
    }

    /**
     * Next of kin getter
     *
     * @return Next of kin
     */
    public String getNextOfKin() {
        return nextOfKin;
    }

    /**
     * Next of kin setter
     *
     * @param nextOfKin Next of kin
     */
    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    /**
     * Next of kin telephone number getter
     *
     * @return Next of kin telephone number
     */
    public String getNextOfKinTelephone() {
        return nextOfKinTelephone;
    }

    /**
     * Next of kin telephone number setter
     *
     * @param nextOfKinTelephone Next of kin telephone number
     */
    public void setNextOfKinTelephone(String nextOfKinTelephone) {
        this.nextOfKinTelephone = nextOfKinTelephone;
    }

    /**
     * To string method for the class
     *
     * @return String containing all class data
     */
    @Override
    public String toString() {
        return "SeniorPlayer{" +
                "nextOfKin='" + nextOfKin + '\'' +
                ", nextOfKinTelephone='" + nextOfKinTelephone + '\'' +
                ", doctor='" + doctor + '\'' +
                ", doctorTelephone='" + doctorTelephone + '\'' +
                ", position='" + position + '\'' +
                ", skills=" + skills.toString() +
                ", healthIssues=" + healthIssues +
                ", UID=" + UID +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", SRUNumber='" + SRUNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
