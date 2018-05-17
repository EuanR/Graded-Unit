package com.simplyrugby.controllers;

import com.simplyrugby.models.Model;
import com.simplyrugby.objects.Member;
import com.simplyrugby.utils.Hash;
import com.simplyrugby.utils.SimpleAlerts;
import com.simplyrugby.utils.UserDocs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    /**
     * The model
     */
    private Model model;
    /**
     * The text field for the user id
     */
    @FXML
    private TextField txtUserID;
    /**
     * The text field for the password
     */
    @FXML
    private PasswordField txtPassword;

    /**
     * Sets the current model
     *
     * @param model The model
     */
    public void setModel(Model model) {
        this.model = model;
    }


    /**
     * Processes a login attempt and displays the coaching home if the login is valid
     *
     * @param event Calling event
     */
    @FXML
    private void btnLoginClickHandler(javafx.event.ActionEvent event) {
        if (validateLogin(txtUserID.getText(), txtPassword.getText())) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Home.fxml"));
                Parent root = fxmlLoader.load();
                HomeController controller = fxmlLoader.getController();
                controller.setModel(this.model);
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
                SimpleAlerts.exceptionAlert("Unexpected error occurred while displaying the home page", e).showAndWait();
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } else {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "Invalid login credentials", "You have provided invalid login credentials. Either your user id or password is incorrect.").showAndWait();
        }
    }

    /**
     * Checks if a id and password combination are valid
     *
     * @param UID      The id from the login attempt
     * @param password The password from the login attempt
     * @return Returns true if the login id and password combination is valid
     */
    private boolean validateLogin(String UID, String password) {
        int ID;
        try {
            ID = Integer.parseInt(UID);
        } catch (NumberFormatException e) {
            SimpleAlerts.exceptionAlert("Invalid ID provided", e).showAndWait();
            return false;
        }
        for (Member coach : model.getCoaches()) {
            if (coach.getUID() == ID && coach.getPassword().equals(Hash.getSha512(password))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Opens the user docs upon the user clicking the hyperlink
     */
    @FXML
    private void openUserDocs() {
        UserDocs.openUserDocs();
    }
}
