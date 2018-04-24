package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.PlayerNotFoundException;
import com.simplyrugby.modals.Model;
import com.simplyrugby.objects.ComboBoxItem;
import com.simplyrugby.objects.Player;
import com.simplyrugby.objects.Squad;
import com.simplyrugby.utils.InputDialog;
import com.simplyrugby.utils.Search;
import com.simplyrugby.utils.SimpleAlerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Euan
 */
public class HomeController {

    /**
     * The model
     */
    Model model;
    private String currentSquadName;
    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox<ComboBoxItem> cmbPlayers;
    @FXML
    private Button btnViewSkills;
    @FXML
    private Label lblSelectPlayerFixed;

    @FXML
    public boolean init(int uid) {
        ArrayList<String> squadsCoaching = new ArrayList<>();
        String squadName = "";
        for (Squad squad : model.getSquads()) {
            if (squad.getCoachID() == uid) {
                squadsCoaching.add(squad.getSquadName());
            }
        }
        if (squadsCoaching.size() <= 0) {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No squads", "You are not coaching any squads!").showAndWait();
            return false;
        }
        if (squadsCoaching.size() > 1) {
            Optional<String> tempSquadName;
            tempSquadName = InputDialog.textInput("Please enter the name of the squad you wish to manage", "Please enter the name of the squad you wish to manage", false, true).showAndWait();
            if (tempSquadName.isPresent()) {
                squadName = tempSquadName.get();
            }
            while (!squadExists(squadName)) {
                tempSquadName = InputDialog.textInput("Please enter the name of the squad you wish to manage", "Please enter the name of the squad you wish to manage", false, true).showAndWait();
                if (tempSquadName.isPresent()) {
                    squadName = tempSquadName.get();
                }
            }
        } else {
            squadName = squadsCoaching.get(0);
        }
        currentSquadName = squadName;
        for (Squad squad : model.getSquads()) {
            if (squad.getSquadName().toLowerCase().equals(squadName.toLowerCase())) {
                for (int playerID : squad.getPlayers()) {
                    try {
                        Player tempPlayer = Search.getPlayerFromID(playerID);
                        ComboBoxItem comboBoxItem = new ComboBoxItem(tempPlayer.getFullName(), tempPlayer.getUID());
                        cmbPlayers.getItems().add(comboBoxItem);
                    } catch (PlayerNotFoundException e) {
                        SimpleAlerts.simpleAlert(Alert.AlertType.ERROR, "Player not found", "A player with that ID does not exist").showAndWait();
                    }
                }
                break;
            }
        }
        return true;
    }

    public void updateSceneTitle() {
        Stage tempStage = (Stage) pane.getScene().getWindow();
        tempStage.setTitle("Simply Rugby Coaching Home - " + currentSquadName);
    }

    @FXML
    private void btnViewSkillsClickHandler(javafx.event.ActionEvent event) {
        if (cmbPlayers.getSelectionModel().isEmpty()) {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No player selected", "You need to select a player").showAndWait();
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/SkillsMenu.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            SkillsMenuController controller = fxmlLoader.getController();
            controller.setModel(this.model);
            controller.init(cmbPlayers.getValue().getPlayerID());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Skills Menu - " + cmbPlayers.getValue().getItemText());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            File tempIconLocation = new File("resources/sru_logo.png");
            Image icon = new Image("file:\\" + tempIconLocation.getCanonicalPath());
            stage.getIcons().add(icon);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private boolean squadExists(String squadName) {
        int index = 0;
        int currentSquadIndex = 0;
        ArrayList<Squad> squads = model.getSquads();
        for (Squad squad : squads) {
            if (squad.getSquadName().toLowerCase().equals(squadName.toLowerCase())) {
                currentSquadIndex = index;
                return true;
            }
            index++;
        }
        return false;
    }

}