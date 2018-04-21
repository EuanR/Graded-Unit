package com.simplyrugby.utils;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;

public final class SimpleAlerts {

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

    public static Alert simpleAlert(Alert.AlertType alertType, String alertTitle, String alertText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertTitle);
        alert.setHeaderText(alertTitle);
        alert.setContentText(alertText);
        return alert;
    }

}