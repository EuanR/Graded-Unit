package com.simplyrugby.objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Euan
 */
public class Member {
    protected int UID = -1;
    protected String firstname = "";
    protected String surname = "";
    protected String address = "";
    protected String postcode = "";
    protected String SRUNumber = "";
    protected LocalDate dateOfBirth;
    protected String telephoneNumber = "";
    protected String mobileNumber = "";
    protected String email = "";


    public Member(int UID, String firstname, String surname, String address, String postcode, String SRUNumber, String dateOfBirth, String telephoneNumber, String mobileNumber, String email) {
        this.UID = UID;
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.postcode = postcode;
        this.SRUNumber = SRUNumber;
        this.telephoneNumber = telephoneNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateFormat);
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getSRUNumber() {
        return SRUNumber;
    }

    public void setSRUNumber(String SRUNumber) {
        this.SRUNumber = SRUNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.firstname + " " + this.surname;
    }

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
