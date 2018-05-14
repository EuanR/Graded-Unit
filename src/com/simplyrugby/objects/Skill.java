package com.simplyrugby.objects;

/**
 * @author Euan
 */
public class Skill {

    /**
     * Name of the skill
     */
    private String skillName;
    /**
     * The skills rating
     */
    private String skillRating;

    /**
     * Constructor
     *
     * @param skillName   Name of the skill
     * @param skillRating The skills rating
     */
    public Skill(String skillName, String skillRating) {
        this.skillName = skillName;
        this.skillRating = skillRating;
    }

    /**
     * Skill rating getter
     *
     * @return Skill rating
     */
    public String getSkillRating() {
        return skillRating;
    }

    /**
     * Skill rating setter
     *
     * @param skillRating Skill rating
     */
    public void setSkillRating(String skillRating) {
        this.skillRating = skillRating;
    }

    /**
     * Skill name getter
     *
     * @return Skill name getter
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * Skill name setter
     *
     * @param skillName Skill name
     */
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    /**
     * To string method for the class
     *
     * @return String containing all class data
     */
    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                ", skillRating=" + skillRating +
                '}';
    }
}
