package com.simplyrugby.objects;

/**
 * @author Euan
 */
public class Skill {

    private String skillName;
    private String skillRating;

    public Skill(String skillName, String skillRating) {
        this.skillName = skillName;
        this.skillRating = skillRating;
    }

    public String getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(String skillRating) {
        this.skillRating = skillRating;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                ", skillRating=" + skillRating +
                '}';
    }
}
