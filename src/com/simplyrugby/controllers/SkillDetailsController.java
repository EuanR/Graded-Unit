package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.SkillCategoryNotFoundException;
import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.Player;
import com.simplyrugby.objects.Skill;
import com.simplyrugby.objects.SkillCategory;
import com.simplyrugby.utils.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
        skillNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                updateSkillName(cellEditEvent.getOldValue().toString(), cellEditEvent.getNewValue().toString());
                modal.exportSystemData();
            }
        });
        TableColumn skillRatingCol = new TableColumn("Skill Rating");
        skillRatingCol.setPrefWidth(100);
        skillRatingCol.setCellValueFactory(new PropertyValueFactory<Skill, String>("skillRating"));
        skillRatingCol.setCellFactory(TextFieldTableCell.<String>forTableColumn());
        skillRatingCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                Skill tempSkill = (Skill) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                updateSkillRating(tempSkill.getSkillName(), cellEditEvent.getOldValue().toString(), cellEditEvent.getNewValue().toString());
                modal.exportSystemData();
            }
        });
        tblSkillData.getColumns().addAll(skillNameCol, skillRatingCol);
        try {
            ObservableList<Skill> dataToAdd = FXCollections.observableArrayList();
            for (Skill skill : Search.getSkillCategoryFromName(skillCategoryName, playerID).getSkills()) {
                dataToAdd.add(skill);
            }
            tblSkillData.setItems(dataToAdd);
        } catch (SkillCategoryNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateSkillName(String currentSkillName, String newSkillName) {
        for (Player player : modal.getPlayers()) {
            if (player.getUID() == playerID) {
                for (SkillCategory skillCategory : player.getSkills()) {
                    for (Skill skill : skillCategory.getSkills()) {
                        if (skill.getSkillName().equals(currentSkillName)) {
                            skill.setSkillName(newSkillName);
                        }
                    }
                }
            }
        }
    }

    private void updateSkillRating(String skillName, String currentSkillRating, String newSkillRating) {
        for (Player player : modal.getPlayers()) {
            if (player.getUID() == playerID) {
                for (SkillCategory skillCategory : player.getSkills()) {
                    for (Skill skill : skillCategory.getSkills()) {
                        if (skill.getSkillName().equals(skillName) && skill.getSkillRating().equals(currentSkillRating)) {
                            skill.setSkillRating(newSkillRating);
                        }
                    }
                }
            }
        }
    }

}
