package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.PlayerNotFoundException;
import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.ComboBoxItem;
import com.simplyrugby.objects.SkillCategory;
import com.simplyrugby.utils.Search;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author Euan
 */
public class SkillsMenuController {

    @FXML
    private Button btnViewDetails;
    @FXML
    private ComboBox<ComboBoxItem> cmbSkillCategories;

    Modal modal;
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
            stage.setTitle("Skills Details -" + cmbSkillCategories.getValue().getItemText());
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
