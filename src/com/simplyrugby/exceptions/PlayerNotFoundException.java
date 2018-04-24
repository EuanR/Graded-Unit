package com.simplyrugby.exceptions;

/**
 * @author Euan
 */
public class PlayerNotFoundException extends Exception {

    /**
     * Parameterless constructor
     */
    public PlayerNotFoundException() {
    }

    /**
     * Exception with a custom message
     *
     * @param message Exception message
     */
    public PlayerNotFoundException(String message) {
        super(message);
    }

}
