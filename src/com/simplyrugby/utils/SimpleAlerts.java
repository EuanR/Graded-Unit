package com.simplyrugby.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * SimpleAlerts utility class to display different types of alert easily
 *
 * @author Euan Riggans
 */
public final class SimpleAlerts {

    /**
     * Creates a javafx alert
     * <pre>
     *     {@code
     *      SimpleAlerts.simpleAlert(Alert.AlertType.ERROR, "Player not found", "A player with that ID does not exist").showAndWait();
     *     }
     * </pre>
     *
     * @param alertType  The type of alert
     * @param alertTitle Title of the alert
     * @param alertText  Text body of the alert
     * @return Returns an instance of a javafx alert ready to be shown
     */
    public static Alert simpleAlert(Alert.AlertType alertType, String alertTitle, String alertText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertTitle);
        alert.setHeaderText(alertTitle);
        alert.setContentText(alertText);
        return alert;
    }

    /**
     * Creates a javafx alert which contains a stacktrace to display the user
     * <pre>
     *     {@code
     *      try {
     *          //Your code
     *      } catch (Exception e) {
     *          SimpleAlerts.exceptionAlert("An unexpected error has occurred", e).showAndWait();
     *      }
     *     }
     * </pre>
     *
     * @param text The alert text
     * @param e    The exception
     * @return Returns an instance of a javafx alert ready to be shown
     */
    public static Alert exceptionAlert(String text, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Unexpected error");
        alert.setHeaderText("An unexpected error has occurred");
        alert.setContentText(text);
        String exceptionText = ExceptionUtils.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);
        return alert;
    }

}