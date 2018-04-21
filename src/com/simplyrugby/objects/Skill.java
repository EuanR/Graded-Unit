package com.simplyrugby.objects;

public class Skill {

    private String skillName = "";
    int skillRating = -1;

    public Skill(String skillName, int skillRating) {
        this.skillName = skillName;
        this.skillRating = skillRating;
    }

    public int getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(int skillRating) {
        this.skillRating = skillRating;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + skillName + '\'' +
                ", skillRating=" + skillRating +
                '}';
    }
}
