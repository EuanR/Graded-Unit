package com.simplyrugby.exceptions;

/**
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
