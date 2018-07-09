package com.simplyrugby.objects;

import com.simplyrugby.utils.Hash;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Member class
 *
 * @author Euan Riggans
 */
public class Member {
    /**
     * Members ID
     */
    protected int UID;
    /**
     * Members first name
     */
    protected String firstname;
    /**
     * Members surname
     */
    protected String surname;
    /**
     * Members address
     */
    protected String address;
    /**
     * Members postcode
     */
    protected String postcode;
    /**
     * SRU number of the member
     */
    protected String SRUNumber;
    /**
     * Members date of birth
     */
    protected LocalDate dateOfBirth;
    /**
     * Members phone number
     */
    protected String telephoneNumber;
    /**
     * Members mobile number
     */
    protected String mobileNumber;
    /**
     * Members email
     */
    protected String email;
    /**
     * Members password
     */
    protected String password;


    /**
     * Constructor
     *
     * @param UID             Members ID
     * @param firstname       Members first name
     * @param surname         Members surname
     * @param address         Members address
     * @param postcode        Members postcode
     * @param SRUNumber       SRU number of the member
     * @param dateOfBirth     Members date of birth
     * @param telephoneNumber Members phone number
     * @param mobileNumber    Members mobile number
     * @param email           Members email
     * @param password        Members password
     */
    public Member(int UID, String firstname, String surname, String address, String postcode, String SRUNumber, String dateOfBirth, String telephoneNumber, String mobileNumber, String email, String password) {
        this.UID = UID;
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.postcode = postcode;
        this.SRUNumber = SRUNumber;
        this.telephoneNumber = telephoneNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.password = Hash.getSha512(password);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateFormat);
    }

    /**
     * UID getter
     *
     * @return The members UID
     */
    public int getUID() {
        return UID;
    }

    /**
     * UID setter
     *
     * @param UID The members UID
     */
    public void setUID(int UID) {
        this.UID = UID;
    }

    /**
     * First name getter
     *
     * @return Members first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * First name setter
     *
     * @param firstname Members first name
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Surname getter
     *
     * @return Members surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Surname setter
     *
     * @param surname Members surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Address getter
     *
     * @return Members address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Address setter
     *
     * @param address Members address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Postcode getter
     *
     * @return Members postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Postcode setter
     *
     * @param postcode Members postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * SRU number getter
     *
     * @return Members SRU number
     */
    public String getSRUNumber() {
        return SRUNumber;
    }

    /**
     * SRU number setter
     *
     * @param SRUNumber Members SRU number
     */
    public void setSRUNumber(String SRUNumber) {
        this.SRUNumber = SRUNumber;
    }

    /**
     * Date of birth getter
     *
     * @return Members date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Date of birth setter
     *
     * @param dateOfBirth Members date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Telephone number getter
     *
     * @return Members telephone number
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Telephone number setter
     *
     * @param telephoneNumber Members telephone number
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Mobile number getter
     *
     * @return Members mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Mobile number setter
     *
     * @param mobileNumber Members mobile number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Email getter
     *
     * @return Members email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Email setter
     *
     * @param email Members email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Full name getter
     *
     * @return Members full name
     */
    public String getFullName() {
        return this.firstname + " " + this.surname;
    }

    /**
     * Password getter
     *
     * @return Members password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter
     *
     * @param password Members password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * To string method for the class
     *
     * @return String containing all class data
     */
    @Override
    public String toString() {
        return "Member{" +
                "UID=" + UID +
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
