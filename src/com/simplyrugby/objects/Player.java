package com.simplyrugby.objects;
import java.util.ArrayList;

public class Player extends Member {

    String doctor = "";
    String doctorTelephone = "";
    String position = "";
    ArrayList<SkillCategory> skills = new ArrayList<SkillCategory>();
    ArrayList<String> healthIssues = new ArrayList<String>();
    public Player(int UID, String firstname, String surname, String address, String postcode, String SRUNumber, String dateOfBirth, String telephoneNumber, String mobileNumber, String email, String doctor, String doctorTelephone, String position, ArrayList<String> healthIssues) {
        super(UID, firstname, surname, address, postcode, SRUNumber, dateOfBirth, telephoneNumber, mobileNumber, email);
        this.doctor = doctor;
        this.doctorTelephone = doctorTelephone;
        this.position = position;
        this.healthIssues = healthIssues;
    }

    public void addSkillCategory(String category) {
        skills.add(new SkillCategory(
           category
        ));
    }

    public ArrayList<SkillCategory> getSkills() {
        return skills;
    }

    public boolean addSkillToCategory(String category, String skill, int rating) {
        boolean found = false;
        for(SkillCategory skillsList : skills) {
            if(skillsList.getCategory().toLowerCase().equals(category.toLowerCase())) {
                found = true;
                skillsList.addSkill(skill, rating);
            }
        }
        if(found) {
           return true;
        } else {
            return false;
        }
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctorTelephone() {
        return doctorTelephone;
    }

    public void setDoctorTelephone(String doctorTelephone) {
        this.doctorTelephone = doctorTelephone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSkills(ArrayList<SkillCategory> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getHealthIssues() {
        return healthIssues;
    }

    public void setHealthIssues(ArrayList<String> healthIssues) {
        this.healthIssues = healthIssues;
    }

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
