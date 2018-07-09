package com.simplyrugby.exceptions;

/**
 * Custom exception for when a player is not found
 *
 * @author Euan Riggans
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
