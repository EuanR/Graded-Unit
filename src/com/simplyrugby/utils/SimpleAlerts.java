package com.simplyrugby.utils;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;

/**
 * @author Euan Riggans
 */
public final class SimpleAlerts {

    /**
     * Creates a JFoenix alert
     * <pre>
     *     {@code
     *      SimpleAlerts.simpleJFXAlert(new JFXAlert((Stage) pane.getScene().getWindow()), "Error", "A player with that ID does not exist!");
     *     }
     * </pre>
     *
     * @param alert      An instance of the JFonix alert type
     * @param alertTitle The title of the alert
     * @param alertText  The text body for the alert
     * @return Returns an instance of the JFoenix alert ready to be shown
     */
    public static JFXAlert simpleJFXAlert(JFXAlert alert, String alertTitle, String alertText) {
        ScrollPane scroll = new ScrollPane();
        scroll.setMaxHeight(250);
        Label contentBody = new Label();
        contentBody.setText(alertText);
        contentBody.setWrapText(true);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(alertTitle));
        content.setBody(contentBody);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXButton closeButton = new JFXButton("ACCEPT");
        closeButton.getStyleClass().add("dialog-accept");
        closeButton.setOnAction(click -> alert.hideWithAnimation());
        content.setActions(closeButton);
        scroll.setContent(content);
        alert.setContent(scroll);
        return alert;
    }

    /**
     * Creates a javafx alert
     * <pre>
     *     {@code
     *      SimpleAlerts.simpleAlert(Alert.AlertType.ERROR, "Player not found", "A player with that ID does not exist").showAndWait();
     *     }
     * </pre>
     * @param alertType     The type of alert
     * @param alertTitle    Title of the alert
     * @param alertText     Text body of the alert
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