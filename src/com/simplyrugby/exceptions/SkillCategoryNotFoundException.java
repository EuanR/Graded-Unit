package com.simplyrugby.exceptions;

/**
 * Custom exception for when a skill category is not found
 *
 * @author Euan
 */
public class SkillCategoryNotFoundException extends Exception {

    /**
     * Parameterless constructor
     */
    public SkillCategoryNotFoundException() {
    }

    /**
     * Exception with a custom message
     *
     * @param message Exception message
     */
    public SkillCategoryNotFoundException(String message) {
        super(message);
    }

}
