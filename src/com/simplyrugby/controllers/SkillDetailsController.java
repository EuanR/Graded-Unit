package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.SkillCategoryNotFoundException;
import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.Skill;
import com.simplyrugby.utils.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

/**
 * @author Euan
 */
public class SkillDetailsController {

    Modal modal;
    private String skillCategoryName;
    private int playerID;

    @FXML
    private TableView tblSkillData;
    @FXML
    private Button btnSaveDetails;
    @FXML
    private AnchorPane pane;

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    public void init(String skillCategoryName, int playerID) {
        this.skillCategoryName = skillCategoryName;
        this.playerID = playerID;
        tblSkillData.setEditable(true);
        TableColumn skillNameCol = new TableColumn("Skill Name");
        skillNameCol.setPrefWidth(100);
        skillNameCol.setCellValueFactory(new PropertyValueFactory<Skill, String>("skillName"));
        skillNameCol.setCellFactory(TextFieldTableCell.<String>forTableColumn());
        TableColumn skillRatingCol = new TableColumn("Skill Rating");
        skillRatingCol.setPrefWidth(100);
        skillRatingCol.setCellValueFactory(new PropertyValueFactory<Skill, Integer>("skillRating"));
        skillRatingCol.setCellFactory(TextFieldTableCell.<Integer>forTableColumn());
        tblSkillData.getColumns().addAll(skillNameCol, skillRatingCol);
        tblSkillData.setPrefSize(250, 200);
        try {
            ObservableList<Skill> dataToAdd = FXCollections.observableArrayList();
            for (Skill skill : Search.getSkillCateogryFromName(skillCategoryName, playerID).getSkills()) {
                dataToAdd.add(skill);
            }
            tblSkillData.setItems(dataToAdd);
        } catch (SkillCategoryNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSaveDetailsClickHandlder(javafx.event.ActionEvent event) {

    }

}
