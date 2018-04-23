package com.simplyrugby.utils;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/**
 * @author Euan
 */
public final class InputDialog {

    /**
     * Creates a TextInputDialog which can be easily displayed to the user to gather input
     * <pre>
     *     {@code
     *      Optional<String> userInput = InputDialog.textInput("Please enter a string", "Please enter a random string", false, false).showAndWait();
     *     }
     * </pre>
     *
     * @param title        The title of the input dialog
     * @param textBody     The main body of text on the input dialog
     * @param resizable    Will the input dialog be resizable
     * @param exitOnCancel If the user clicks the cancel button, should the program exit
     * @return An pre-made TextInputDialog ready to be shown and gather user input
     */
    public static TextInputDialog textInput(String title, String textBody, boolean resizable, boolean exitOnCancel) {
        TextInputDialog textInputDialog = new TextInputDialog();
        if (exitOnCancel) {
            Button cancel = (Button) textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancel.addEventFilter(ActionEvent.ACTION, event ->
                    System.exit(0)
            );
        }
        textInputDialog.setResizable(resizable);
        textInputDialog.setHeaderText(textBody);
        textInputDialog.setTitle(title);
        return textInputDialog;
    }

}
