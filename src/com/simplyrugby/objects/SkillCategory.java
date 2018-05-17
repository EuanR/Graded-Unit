package com.simplyrugby.objects;

import java.util.ArrayList;

/**
 * Skill Category class
 *
 * @author Euan
 */
public class SkillCategory {

    /**
     * The name of the skill category
     */
    protected String category;
    /**
     * Notes for the player regarding this skill category
     */
    protected ArrayList<String> notes = new ArrayList<>();
    /**
     * Skills contained within this skill category
     */
    protected ArrayList<Skill> skills = new ArrayList<>();

    /**
     * Constructor
     *
     * @param category The name of the skill category
     */
    public SkillCategory(String category) {
        this.category = category;
    }

    /**
     * Adds a new skill to the skill category
     *
     * @param skillName   The name of the new skill
     * @param skillRating The new skills rating
     */
    public void addSkill(String skillName, String skillRating) {
        skills.add(new Skill(
                skillName,
                skillRating
        ));
    }

    /**
     * Notes getter
     *
     * @return Players notes
     */
    public ArrayList<String> getNotes() {
        return notes;
    }

    /**
     * Add a new note
     *
     * @param notes Players notes
     */
    public void addNotes(String notes) {
        this.notes.add(notes);
    }

    /**
     * Skills getter
     *
     * @return The skills from this category
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Skill category name getter
     *
     * @return Skill category name
     */
    public String getCategory() {
        return category;
    }

    /**
     * To string method for the class
     *
     * @return String containing all class data
     */
    @Override
    public String toString() {
        return "SkillCategory{" +
                "category='" + category + '\'' +
                ", notes=" + notes +
                ", skills=" + skills +
                '}';
    }
}
