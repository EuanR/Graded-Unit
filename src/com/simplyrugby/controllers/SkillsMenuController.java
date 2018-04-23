package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.PlayerNotFoundException;
import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.ComboBoxItem;
import com.simplyrugby.objects.SkillCategory;
import com.simplyrugby.utils.Search;
import com.simplyrugby.utils.SimpleAlerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/**
 * @author Euan
 */
public class SkillsMenuController {

    Modal modal;
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
            File tempIconLocation = new File("resources/sru_logo.png");
            Image icon = new Image("file:\\" + tempIconLocation.getCanonicalPath());
            stage.getIcons().add(icon);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddNotesClickHandler() {

    }

    @FXML
    private void btnViewNotesClickHandler(javafx.event.ActionEvent event) {
        if (cmbSkillCategories.getSelectionModel().isEmpty()) {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No skill category selected", "You need to select a skill category").showAndWait();
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Notes.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            NotesController controller = fxmlLoader.getController();
            controller.setModal(this.modal);
            controller.init(playerID, cmbSkillCategories.getValue().getItemText());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            try {
                stage.setTitle("Notes for - " + Search.getPlayerFromID(playerID).getFullName() + " - " + cmbSkillCategories.getValue().getItemText());
            } catch (PlayerNotFoundException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            File tempIconLocation = new File("resources/sru_logo.png");
            Image icon = new Image("file:\\" + tempIconLocation.getCanonicalPath());
            stage.getIcons().add(icon);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if (cmbSkillCategories.getSelectionModel().isEmpty()) {
//            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No skill category selected", "You need to select a skill category").showAndWait();
//            return;
//        }
//        StringBuilder notesStringBuilder = new StringBuilder();
//        for (Player player : modal.getPlayers()) {
//            if (player.getUID() == playerID) {
//                for (SkillCategory skillCategory : player.getSkills()) {
//                    if (skillCategory.getCategory().equals(cmbSkillCategories.getValue().getItemText())) {
//                        for (String notes : skillCategory.getNotes()) {
//                            notesStringBuilder.append(notes);
//                            notesStringBuilder.append("\n");
//                        }
//                    }
//                }
//            }
//        }
//        if (notesStringBuilder.length() <= 0) {
//            try {
//                SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No notes found", "No notes found for " + Search.getPlayerFromID(playerID).getFullName() + " regarding " + cmbSkillCategories.getValue().getItemText().toLowerCase() + " skill").showAndWait();
//            } catch (PlayerNotFoundException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "Notes for " + Search.getPlayerFromID(playerID).getFullName() + " regarding " + cmbSkillCategories.getValue().getItemText().toLowerCase() + " skill", notesStringBuilder.toString()).showAndWait();
//            } catch (PlayerNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
