package com.simplyrugby.controllers;

import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.Member;
import com.simplyrugby.utils.Hash;
import com.simplyrugby.utils.SimpleAlerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/**
 * @author Euan
 */
public class LoginController {
    Modal modal;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUserID;
    @FXML
    private PasswordField txtPassword;

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    @FXML
    private void btnLoginClickHandler(javafx.event.ActionEvent event) {
        if (validateLogin(txtUserID.getText(), txtPassword.getText())) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Home.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                HomeController controller = fxmlLoader.getController();
                controller.setModal(this.modal);
                if (!controller.init(Integer.parseInt(txtUserID.getText()))) {
                    return;
                }
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Simply Rugby Coaching Home");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                File tempIconLocation = new File("resources/sru_logo.png");
                Image icon = new Image("file:\\" + tempIconLocation.getCanonicalPath());
                stage.getIcons().add(icon);
                stage.show();
                controller.updateSceneTitle();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "Invalid login credentials", "You have provided invalid login credentials. Either your user id or password is incorrect.").showAndWait();
            return;
        }
    }

    private boolean validateLogin(String UID, String password) {
        int ID;
        try {
            ID = Integer.parseInt(UID);
        } catch (NumberFormatException e) {
            return false;
        }
        for (Member coach : modal.getCoaches()) {
            if (coach.getUID() == ID && coach.getPassword().equals(Hash.getSha512(password))) {
                return true;
            }
        }
        return false;
    }
}
