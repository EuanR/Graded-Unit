package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.SkillCategoryNotFoundException;
import com.simplyrugby.models.Model;
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

    /**
     * The model
     */
    Model model;
    /**
     * The skill category being displayed
     */
    private String skillCategoryName;
    /**
     * The player id of the player who's skills are being displayed
     */
    private int playerID;

    /**
     * The table containing the skills and their ratings
     */
    @FXML
    private TableView tblSkillData;
    /**
     * The pane of the view
     */
    @FXML
    private AnchorPane pane;

    /**
     * Sets the current model
     *
     * @param model The model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Initialising the form and adding the skill data to the table and setting up edit event handlers
     *
     * @param skillCategoryName The skill category
     * @param playerID          The player id
     */
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
                if (cellEditEvent.getNewValue().toString().isEmpty()) {
                    tblSkillData.refresh();
                    return;
                }
                updateSkillName(cellEditEvent.getOldValue().toString(), cellEditEvent.getNewValue().toString());
                model.exportSystemData();
                ((Skill) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow())).setSkillName(cellEditEvent.getNewValue().toString());
                tblSkillData.refresh();
            }
        });
        TableColumn skillRatingCol = new TableColumn("Skill Rating");
        skillRatingCol.setPrefWidth(100);
        skillRatingCol.setCellValueFactory(new PropertyValueFactory<Skill, String>("skillRating"));
        skillRatingCol.setCellFactory(TextFieldTableCell.<String>forTableColumn());
        skillRatingCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                if (cellEditEvent.getNewValue().toString().isEmpty()) {
                    tblSkillData.refresh();
                    return;
                }
                try {
                    int tempNewRating = Integer.parseInt(cellEditEvent.getNewValue().toString());
                    if (tempNewRating > 10 || tempNewRating < 0) {
                        tblSkillData.refresh();
                        return;
                    }
                } catch (Exception e) {
                    tblSkillData.refresh();
                    return;
                }
                Skill tempSkill = (Skill) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                updateSkillRating(tempSkill.getSkillName(), cellEditEvent.getOldValue().toString(), cellEditEvent.getNewValue().toString());
                model.exportSystemData();
                ((Skill) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow())).setSkillRating(cellEditEvent.getNewValue().toString());
                tblSkillData.refresh();

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

    /**
     * Updating the skill name to the new one provided
     *
     * @param currentSkillName The current skill name
     * @param newSkillName     The new skill name
     */
    private void updateSkillName(String currentSkillName, String newSkillName) {
        for (Player player : model.getPlayers()) {
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

    /**
     * Updating the skill rating to the new one provided
     *
     * @param skillName          The skill name
     * @param currentSkillRating The current skill rating
     * @param newSkillRating     The new skill rating
     */
    private void updateSkillRating(String skillName, String currentSkillRating, String newSkillRating) {
        for (Player player : model.getPlayers()) {
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
