package com.simplyrugby.exceptions;

/**
 * @author Euan
 */
public class PlayerNotFoundException extends Exception {

    public PlayerNotFoundException() {
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }

}
