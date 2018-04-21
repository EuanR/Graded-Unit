package com.simplyrugby.objects;

import java.util.ArrayList;

public class JuniorPlayer extends Player {

    String doctorsAddress;
    Boolean parentalConsentFormSigned = false;
    private ArrayList<Guardian> guardianData = new ArrayList<Guardian>();

    public JuniorPlayer(int UID, String firstname, String surname, String address, String postcode, String SRUNumber, String dateOfBirth, String telephoneNumber, String mobileNumber, String email, String doctor, String doctorTelephone, String position, ArrayList<String> healthIssues, String guardianOneName, String guardianOneRelationship, String guardianOneAddress, String guardianOneTelephone, String guardianTwoName, String guardianTwoRelationship, String guardianTwoAddress, String guardianTwoTelephone, String doctorsAddress, Boolean parentalConsentFormSigned) {
        super(UID, firstname, surname, address, postcode, SRUNumber, dateOfBirth, telephoneNumber, mobileNumber, email, doctor, doctorTelephone, position, healthIssues);
        this.doctorsAddress = doctorsAddress;
        this.parentalConsentFormSigned = parentalConsentFormSigned;
        guardianData.add(new Guardian(
                guardianOneName,
                guardianOneRelationship,
                guardianOneAddress,
                guardianOneTelephone
        ));
        guardianData.add(new Guardian(
                guardianTwoName,
                guardianTwoRelationship,
                guardianTwoAddress,
                guardianTwoTelephone
        ));
    }

    public String getDoctorsAddress() {
        return doctorsAddress;
    }

    public void setDoctorsAddress(String doctorsAddress) {
        this.doctorsAddress = doctorsAddress;
    }

    public Boolean getParentalConsentFormSigned() {
        return parentalConsentFormSigned;
    }

    public void setParentalConsentFormSigned(Boolean parentalConsentFormSigned) {
        this.parentalConsentFormSigned = parentalConsentFormSigned;
    }

    public ArrayList<Guardian> getGuardianData() {
        return guardianData;
    }

    public void setGuardianData(ArrayList<Guardian> guardianData) {
        this.guardianData = guardianData;
    }

    @Override
    public String toString() {
        return "JuniorPlayer{" +
                "doctorsAddress='" + doctorsAddress + '\'' +
                ", parentalConsentFormSigned=" + parentalConsentFormSigned +
                ", guardianData=" + guardianData +
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
