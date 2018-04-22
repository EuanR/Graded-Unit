package com.simplyrugby.controllers;

import com.simplyrugby.modals.Modal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author Euan
 */
public class LoginController {
    Modal modal;

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUserID;

    @FXML
    private void btnLoginClickHandler(javafx.event.ActionEvent event) {
        if (validateLogin(txtUserID.getText())) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Home.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                HomeController controller = fxmlLoader.getController();
                controller.setModal(this.modal);
                controller.init();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Simply Rugby Coaching Home");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Invalid login credentials", ButtonType.OK);
            alert.initStyle(StageStyle.DECORATED);
            alert.showAndWait();
            return;
        }
    }

    private boolean validateLogin(String UID) {
        int ID = 0;
        try {
            ID = Integer.parseInt(UID);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Not a valid ID", ButtonType.OK);
            alert.showAndWait();
            return false;
        }
        if (modal.getCoach().getUID() == ID) {
            return true;
        } else {
            return false;
        }
    }
}
