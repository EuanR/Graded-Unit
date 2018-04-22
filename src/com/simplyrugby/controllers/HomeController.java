package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.PlayerNotFoundException;
import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.ComboBoxItem;
import com.simplyrugby.objects.Player;
import com.simplyrugby.objects.Squad;
import com.simplyrugby.utils.Search;
import com.simplyrugby.utils.SimpleAlerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Euan
 */
public class HomeController {

    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox<ComboBoxItem> cmbPlayers;
    @FXML
    private Button btnViewSkills;
    @FXML
    private Label lblSelectPlayerFixed;

    Modal modal;

    int currentSquadIndex = 0;

    @FXML
    public void init() {
        TextInputDialog getSquad = new TextInputDialog();
        Optional<String> tempSquadName = null;
        Button cancel = (Button) getSquad.getDialogPane().lookupButton(ButtonType.CANCEL);
        String squadName = "";
        cancel.addEventFilter(ActionEvent.ACTION, event ->
                System.exit(0)
        );
        getSquad.setResizable(false);
        getSquad.setHeaderText("Please enter the name of the squad you wish to manage");
        getSquad.setTitle("Please enter the name of the squad you wish to manage");
        tempSquadName = getSquad.showAndWait();
        if (tempSquadName.isPresent()) {
            squadName = tempSquadName.get();
        }
        while (!squadExists(squadName)) {
            getSquad.setResizable(true);
            getSquad.setHeaderText("Please enter the name of the squad you wish to manage");
            getSquad.setTitle("Please enter the name of the squad you wish to manage");
            tempSquadName = getSquad.showAndWait();
            if (tempSquadName.isPresent()) {
                squadName = tempSquadName.get();
            }
        }
        for (Squad squad : modal.getSquads()) {
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
            controller.setModal(this.modal);
            controller.init(cmbPlayers.getValue().getPlayerID());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Skills Menu - " + cmbPlayers.getValue().getItemText());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    private boolean squadExists(String squadName) {
        int index = 0;
        ArrayList<Squad> squads = modal.getSquads();
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