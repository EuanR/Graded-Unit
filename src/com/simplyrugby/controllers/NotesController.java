package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.PlayerNotFoundException;
import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.Player;
import com.simplyrugby.objects.SkillCategory;
import com.simplyrugby.utils.InputDialog;
import com.simplyrugby.utils.Search;
import com.simplyrugby.utils.SimpleAlerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.util.Optional;

/**
 * @author Euan
 */
public class NotesController {

    Modal modal;
    private int playerID;
    private String currentSkillCategory;
    @FXML
    private ListView lstNotes;
    @FXML
    private Button btnDeleteSelected;
    @FXML
    private Button btnAddNotes;

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    @FXML
    public void init(int playerID, String currentSkillCategory) {
        this.playerID = playerID;
        this.currentSkillCategory = currentSkillCategory;
        addNotesToListBox(playerID, currentSkillCategory);
        if (lstNotes.getItems().size() <= 0) {
            try {
                SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No notes found", "There are no notes for " + Search.getPlayerFromID(playerID).getFullName() + " regarding the " + currentSkillCategory.toLowerCase()).showAndWait();
            } catch (PlayerNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnAddNotesClickHandler() {
        Optional<String> tempSquadName = null;
        String notes;
        try {
            tempSquadName = InputDialog.textInput("Please enter the note you wish to add", "Please enter the note you wish to add for " + Search.getPlayerFromID(playerID).getFullName() + " regarding " + currentSkillCategory.toLowerCase() + " skill", false, false).showAndWait();
        } catch (PlayerNotFoundException e) {
            e.printStackTrace();
        }
        if (tempSquadName.isPresent()) {
            notes = tempSquadName.get();
            if (checkForDuplicateNote(notes)) {
                try {
                    SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "Note already exists", "That note already exists for " + Search.getPlayerFromID(playerID).getFullName()).showAndWait();
                } catch (PlayerNotFoundException e) {
                    e.printStackTrace();
                }
                return;
            }
            for (Player player : modal.getPlayers()) {
                if (player.getUID() == playerID) {
                    for (SkillCategory skillCategory : player.getSkills()) {
                        if (skillCategory.getCategory().equals(currentSkillCategory)) {
                            skillCategory.addNotes(notes);
                            modal.exportSystemData();
                            updateNotes();
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void btnDeleteSelectedClickHandler() {
        String currentlySelectedNote;
        int indexToRemove = 0;
        if (lstNotes.getSelectionModel().getSelectedIndex() <= -1) {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No note selected", "You need to select a note to delete").showAndWait();
            return;
        }
        currentlySelectedNote = lstNotes.getSelectionModel().getSelectedItem().toString();
        Alert editOptions = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you wish to delete the following note: " + currentlySelectedNote.toLowerCase() + "?", ButtonType.OK, ButtonType.CANCEL);
        editOptions.setTitle("Delete Note");
        editOptions.showAndWait();
        if (editOptions.getResult() == ButtonType.OK) {
            for (Player player : modal.getPlayers()) {
                if (player.getUID() == playerID) {
                    for (SkillCategory skillCategory : player.getSkills()) {
                        if (skillCategory.getCategory().equals(currentSkillCategory)) {
                            for (String notes : skillCategory.getNotes()) {
                                if (notes.toLowerCase().equals(currentlySelectedNote.toLowerCase())) {
                                    skillCategory.getNotes().remove(indexToRemove);
                                    modal.exportSystemData();
                                    updateNotes();
                                    return;
                                }
                                indexToRemove++;
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateNotes() {
        lstNotes.getItems().clear();
        addNotesToListBox(playerID, currentSkillCategory);
    }

    private void addNotesToListBox(int playerID, String currentSkillCategory) {
        for (Player player : modal.getPlayers()) {
            if (player.getUID() == playerID) {
                for (SkillCategory skillCategory : player.getSkills()) {
                    if (skillCategory.getCategory().equals(currentSkillCategory)) {
                        for (String notes : skillCategory.getNotes()) {
                            lstNotes.getItems().add(notes);
                        }
                    }
                }
            }
        }
    }

    private boolean checkForDuplicateNote(String noteText) {
        for (Player player : modal.getPlayers()) {
            if (player.getUID() == playerID) {
                for (SkillCategory skillCategory : player.getSkills()) {
                    if (skillCategory.getCategory().equals(currentSkillCategory)) {
                        for (String notes : skillCategory.getNotes()) {
                            if (notes.toLowerCase().equals(noteText.toLowerCase())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
