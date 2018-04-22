package com.simplyrugby.utils;

import javafx.scene.control.Alert;

/**
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

}