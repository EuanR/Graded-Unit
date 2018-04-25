package com.simplyrugby.objects;

import java.util.ArrayList;

/**
 * @author Euan
 */
public class JuniorPlayer extends Player {

    /**
     * Players doctors address
     */
    String doctorsAddress;
    /**
     * Whether the consent form has been signed
     */
    Boolean parentalConsentFormSigned = false;
    /**
     * List of players guardian(s)
     */
    private ArrayList<Guardian> guardianData = new ArrayList<Guardian>();

    /**
     * Constructor
     *
     * @param UID                       Members ID
     * @param firstname                 Members first name
     * @param surname                   Members surname
     * @param address                   Members address
     * @param postcode                  Members postcode
     * @param SRUNumber                 SRU number of the member
     * @param dateOfBirth               Members date of birth
     * @param telephoneNumber           Members phone number
     * @param mobileNumber              Members mobile number
     * @param email                     Members email
     * @param password                  Members password
     * @param doctor                    Members doctor
     * @param doctorTelephone           Doctors telephone number
     * @param position                  Players position
     * @param healthIssues              List of any health issues
     * @param guardianOneName           First guardian of player name
     * @param guardianOneRelationship   Guardian relationship to player
     * @param guardianOneAddress        Guardian address
     * @param guardianOneTelephone      Guardian telephone number
     * @param guardianTwoName           Second guardian of players name
     * @param guardianTwoRelationship   Guardian relationship to player
     * @param guardianTwoAddress        Guardian address
     * @param guardianTwoTelephone      Guardian telephone number
     * @param doctorsAddress            Second guardian of players name
     * @param parentalConsentFormSigned Has the consent form been signed
     */
    public JuniorPlayer(int UID, String firstname, String surname, String address, String postcode, String SRUNumber, String dateOfBirth, String telephoneNumber, String mobileNumber, String email, String password, String doctor, String doctorTelephone, String position, ArrayList<String> healthIssues, String guardianOneName, String guardianOneRelationship, String guardianOneAddress, String guardianOneTelephone, String guardianTwoName, String guardianTwoRelationship, String guardianTwoAddress, String guardianTwoTelephone, String doctorsAddress, Boolean parentalConsentFormSigned) {
        super(UID, firstname, surname, address, postcode, SRUNumber, dateOfBirth, telephoneNumber, mobileNumber, email, password, doctor, doctorTelephone, position, healthIssues);
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

    /**
     * Doctors address getter
     *
     * @return Doctors address
     */
    public String getDoctorsAddress() {
        return doctorsAddress;
    }

    /**
     * Doctors address setter
     *
     * @param doctorsAddress Doctors address
     */
    public void setDoctorsAddress(String doctorsAddress) {
        this.doctorsAddress = doctorsAddress;
    }

    /**
     * Whether the consent for has been sigened getter
     *
     * @return Whether the consent for has been sigened
     */
    public Boolean getParentalConsentFormSigned() {
        return parentalConsentFormSigned;
    }

    /**
     * Whether the consent for has been sigened setter
     *
     * @param parentalConsentFormSigned Whether the consent for has been sigened
     */
    public void setParentalConsentFormSigned(Boolean parentalConsentFormSigned) {
        this.parentalConsentFormSigned = parentalConsentFormSigned;
    }

    /**
     * Guardian data getter
     *
     * @return Guardian data
     */
    public ArrayList<Guardian> getGuardianData() {
        return guardianData;
    }

    /**
     * Guardian data setter
     *
     * @param guardianData Guardian data
     */
    public void setGuardianData(ArrayList<Guardian> guardianData) {
        this.guardianData = guardianData;
    }

    /**
     * To string method for the class
     *
     * @return String containing all class data
     */
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
