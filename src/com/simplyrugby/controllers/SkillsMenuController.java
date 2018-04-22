package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.PlayerNotFoundException;
import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.ComboBoxItem;
import com.simplyrugby.objects.Player;
import com.simplyrugby.objects.SkillCategory;
import com.simplyrugby.utils.Search;
import com.simplyrugby.utils.SimpleAlerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Euan
 */
public class SkillsMenuController {

    Modal modal;
    @FXML
    private Button btnAddNotes;
    @FXML
    private Button btnViewNotes;
    @FXML
    private Button btnViewDetails;
    @FXML
    private ComboBox<ComboBoxItem> cmbSkillCategories;
    private int playerID;

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    @FXML
    public void init(int playerID) {
        this.playerID = playerID;
        try {
            for (SkillCategory skillCategory : Search.getPlayerFromID(playerID).getSkills()) {
                ComboBoxItem comboBoxItem = new ComboBoxItem(skillCategory.getCategory(), 0);
                cmbSkillCategories.getItems().add(comboBoxItem);
            }
        } catch (PlayerNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnViewDetailsClickHandler(javafx.event.ActionEvent event) {
        if (cmbSkillCategories.getSelectionModel().isEmpty()) {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No skill category selected", "You need to select a skill category").showAndWait();
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/SkillDetails.fxml"));
            Parent root = null;
            root = (Parent) fxmlLoader.load();
            SkillDetailsController controller = fxmlLoader.getController();
            controller.setModal(this.modal);
            controller.init(cmbSkillCategories.getValue().getItemText(), playerID);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Skills Details - " + cmbSkillCategories.getValue().getItemText());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddNotesClickHandler() {
        if (cmbSkillCategories.getSelectionModel().isEmpty()) {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No skill category selected", "You need to select a skill category").showAndWait();
            return;
        }
        TextInputDialog newNotes = new TextInputDialog();
        Optional<String> tempSquadName = null;
        Button cancel = (Button) newNotes.getDialogPane().lookupButton(ButtonType.CANCEL);
        String notes = "";
        newNotes.setResizable(false);
        try {
            newNotes.setHeaderText("Please enter the note you wish to add for " + Search.getPlayerFromID(playerID).getFullName() + " regarding " + cmbSkillCategories.getValue().getItemText().toLowerCase() + " skill");
        } catch (PlayerNotFoundException e) {
            e.printStackTrace();
        }
        newNotes.setTitle("Please enter the note you wish to add");
        tempSquadName = newNotes.showAndWait();
        if (tempSquadName.isPresent()) {
            notes = tempSquadName.get();
            for (Player player : modal.getPlayers()) {
                if (player.getUID() == playerID) {
                    for (SkillCategory skillCategory : player.getSkills()) {
                        if (skillCategory.getCategory().equals(cmbSkillCategories.getValue().getItemText())) {
                            skillCategory.addNotes(notes);
                            modal.exportSystemData();
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void btnViewNotesClickHandler(javafx.event.ActionEvent event) {
        if (cmbSkillCategories.getSelectionModel().isEmpty()) {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No skill category selected", "You need to select a skill category").showAndWait();
            return;
        }
        StringBuilder notesStringBuilder = new StringBuilder();
        for (Player player : modal.getPlayers()) {
            if (player.getUID() == playerID) {
                for (SkillCategory skillCategory : player.getSkills()) {
                    if (skillCategory.getCategory().equals(cmbSkillCategories.getValue().getItemText())) {
                        for (String notes : skillCategory.getNotes()) {
                            notesStringBuilder.append(notes);
                            notesStringBuilder.append("\n");
                        }
                    }
                }
            }
        }
        if (notesStringBuilder.length() <= 0) {
            try {
                SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No notes found", "No notes found for " + Search.getPlayerFromID(playerID).getFullName() + " regarding " + cmbSkillCategories.getValue().getItemText().toLowerCase() + " skill").showAndWait();
            } catch (PlayerNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "Notes for " + Search.getPlayerFromID(playerID).getFullName() + " regarding " + cmbSkillCategories.getValue().getItemText().toLowerCase() + " skill", notesStringBuilder.toString()).showAndWait();
            } catch (PlayerNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
