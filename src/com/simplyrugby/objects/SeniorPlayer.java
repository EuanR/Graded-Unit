package com.simplyrugby.objects;

import java.util.ArrayList;

/**
 * @author Euan
 */
public class SeniorPlayer extends Player {
    String nextOfKin = "";
    String nextOfKinTelephone = "";

    public SeniorPlayer(int UID, String firstname, String surname, String address, String postcode, String SRUNumber, String dateOfBirth, String telephoneNumber, String mobileNumber, String email, String doctor, String doctorTelephone, String position, ArrayList<String> healthIssues, String nextOfKin, String nextOfKinTelephone) {
        super(UID, firstname, surname, address, postcode, SRUNumber, dateOfBirth, telephoneNumber, mobileNumber, email, doctor, doctorTelephone, position, healthIssues);
        this.nextOfKin = nextOfKin;
        this.nextOfKinTelephone = nextOfKinTelephone;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getNextOfKinTelephone() {
        return nextOfKinTelephone;
    }

    public void setNextOfKinTelephone(String nextOfKinTelephone) {
        this.nextOfKinTelephone = nextOfKinTelephone;
    }

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
