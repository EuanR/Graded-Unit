package com.simplyrugby.objects;

import java.util.ArrayList;

public class SkillCategory {

    protected String category = "";
    protected ArrayList<String> notes = new ArrayList<String>();
    protected ArrayList<Skill> skills = new ArrayList<Skill>();

    public SkillCategory(String category) {
        this.category = category;
    }

    public void addSkill(String skillName, int skillRating) {
        skills.add(new Skill(
                skillName,
                skillRating
        ));
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void addNotes(String notes) {
        this.notes.add(notes);
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "SkillCategory{" +
                "category='" + category + '\'' +
                ", notes=" + notes +
                ", skills=" + skills +
                '}';
    }
}
