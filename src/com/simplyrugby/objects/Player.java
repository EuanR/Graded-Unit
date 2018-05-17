package com.simplyrugby.objects;

import java.util.ArrayList;

/**
 * Player class
 *
 * @author Euan
 */
public class Player extends Member {

    /**
     * Members doctor
     */
    protected String doctor = "";
    /**
     * Doctors telephone number
     */
    protected String doctorTelephone = "";
    /**
     * Players position
     */
    protected String position = "";
    /**
     * List of players skills
     */
    protected ArrayList<SkillCategory> skills = new ArrayList<SkillCategory>();
    /**
     * List of any health issues
     */
    protected ArrayList<String> healthIssues;

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
     * @param doctor          Members doctor
     * @param doctorTelephone Doctors telephone number
     * @param position        Players position
     * @param healthIssues    List of any health issues
     */
    public Player(int UID, String firstname, String surname, String address, String postcode, String SRUNumber, String dateOfBirth, String telephoneNumber, String mobileNumber, String email, String password, String doctor, String doctorTelephone, String position, ArrayList<String> healthIssues) {
        super(UID, firstname, surname, address, postcode, SRUNumber, dateOfBirth, telephoneNumber, mobileNumber, email, password);
        this.doctor = doctor;
        this.doctorTelephone = doctorTelephone;
        this.position = position;
        this.healthIssues = healthIssues;
    }

    /**
     * Adds a new skill category to the player
     *
     * @param category The category to add
     */
    public void addSkillCategory(String category) {
        skills.add(new SkillCategory(
                category
        ));
    }

    /**
     * Gets all players skill categories
     *
     * @return Returns a list of the players skill categories
     */
    public ArrayList<SkillCategory> getSkills() {
        return skills;
    }

    /**
     * Skill category list setter
     *
     * @param skills Skill category list
     */
    public void setSkills(ArrayList<SkillCategory> skills) {
        this.skills = skills;
    }

    public boolean addSkillToCategory(String category, String skill, String rating) {
        boolean found = false;
        for (SkillCategory skillsList : skills) {
            if (skillsList.getCategory().toLowerCase().equals(category.toLowerCase())) {
                found = true;
                skillsList.addSkill(skill, rating);
            }
        }
        if (found) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Players doctor getter
     *
     * @return Players doctor
     */
    public String getDoctor() {
        return doctor;
    }

    /**
     * Players doctor setter
     *
     * @param doctor Players doctor
     */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
     * Players doctor telephone number getter
     *
     * @return Players doctor telephone number
     */
    public String getDoctorTelephone() {
        return doctorTelephone;
    }

    /**
     * Players doctor telephone number setter
     *
     * @param doctorTelephone Players doctor telephone number
     */
    public void setDoctorTelephone(String doctorTelephone) {
        this.doctorTelephone = doctorTelephone;
    }

    /**
     * Players position getter
     *
     * @return Players position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Players position setter
     *
     * @param position Players position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Players health issues getter
     *
     * @return Players health issues
     */
    public ArrayList<String> getHealthIssues() {
        return healthIssues;
    }

    /**
     * Players health issues setter
     *
     * @param healthIssues Players health issues
     */
    public void setHealthIssues(ArrayList<String> healthIssues) {
        this.healthIssues = healthIssues;
    }

    /**
     * To string method for the class
     *
     * @return String containing all class data
     */
    @Override
    public String toString() {
        return "Player{" +
                "doctor='" + doctor + '\'' +
                ", doctorTelephone='" + doctorTelephone + '\'' +
                ", position='" + position + '\'' +
                ", skills=" + skills +
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
