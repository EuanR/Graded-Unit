package com.simplyrugby.controllers;

import com.simplyrugby.exceptions.PlayerNotFoundException;
import com.simplyrugby.models.Model;
import com.simplyrugby.objects.Player;
import com.simplyrugby.objects.SkillCategory;
import com.simplyrugby.utils.InputDialog;
import com.simplyrugby.utils.Search;
import com.simplyrugby.utils.SimpleAlerts;
import com.simplyrugby.utils.UserDocs;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.util.Optional;

/**
 *  Controller for the notes view
 *
 * @author Euan
 */
public class NotesController {

    /**
     * The model
     */
    private Model model;
    /**
     * The player id of the player who's skills are being displayed
     */
    private int playerID;
    /**
     * The skill category being displayed
     */
    private String currentSkillCategory;
    /**
     * List view containing the players skill notes
     */
    @FXML
    private ListView lstNotes;

    /**
     * Sets the current model
     *
     * @param model The model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Initialising the view and fetching the players current skill notes
     *
     * @param playerID             The players id
     * @param currentSkillCategory The skill category
     */
    @FXML
    public void init(int playerID, String currentSkillCategory) {
        this.playerID = playerID;
        this.currentSkillCategory = currentSkillCategory;
        addNotesToListView(playerID, currentSkillCategory);
        if (lstNotes.getItems().size() <= 0) {
            try {
                SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No notes found", "There are no notes for " + Search.getPlayerFromID(playerID).getFullName() + " regarding the " + currentSkillCategory.toLowerCase()).showAndWait();
            } catch (PlayerNotFoundException e) {
                SimpleAlerts.exceptionAlert("Unable to find a player with that ID", e);
            }
        }
    }

    /**
     * Prompts the user for a note to add to the current users skill category
     */
    @FXML
    private void btnAddNotesClickHandler() {
        Optional<String> tempSquadName = Optional.empty();
        String notes;
        try {
            tempSquadName = InputDialog.textInput("Please enter the note you wish to add", "Please enter the note you wish to add for " + Search.getPlayerFromID(playerID).getFullName() + " regarding " + currentSkillCategory.toLowerCase() + " skill", false, false).showAndWait();
        } catch (PlayerNotFoundException e) {
            SimpleAlerts.exceptionAlert("Unexpected error has occurred when attempting to add a note for that player", e);
        }
        if (tempSquadName.isPresent()) {
            notes = tempSquadName.get();
            if (checkForDuplicateNote(notes)) {
                try {
                    SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "Note already exists", "That note already exists for " + Search.getPlayerFromID(playerID).getFullName()).showAndWait();
                } catch (PlayerNotFoundException e) {
                    SimpleAlerts.exceptionAlert("Unexpected error has occurred", e);
                }
                return;
            }
            for (Player player : model.getPlayers()) {
                if (player.getUID() == playerID) {
                    for (SkillCategory skillCategory : player.getSkills()) {
                        if (skillCategory.getCategory().equals(currentSkillCategory)) {
                            skillCategory.addNotes(notes);
                            model.exportSystemData();
                            updateNotes();
                        }
                    }
                }
            }
        }
    }

    /**
     * Deletes the selected note after the user confirms their action
     */
    @FXML
    private void btnDeleteSelectedClickHandler() {
        String currentlySelectedNote;
        int indexToRemove = 0;
        if (lstNotes.getSelectionModel().getSelectedIndex() <= -1) {
            SimpleAlerts.simpleAlert(Alert.AlertType.INFORMATION, "No note selected", "You need to select a note to delete").showAndWait();
            return;
        }
        currentlySelectedNote = lstNotes.getSelectionModel().getSelectedItem().toString();
        Optional<ButtonType> confirmation = InputDialog.confirmationDialog("Are you sure you wish to delete the following note: " + currentlySelectedNote.toLowerCase() + "?", false).showAndWait();
        if (confirmation.get() == ButtonType.OK) {
            for (Player player : model.getPlayers()) {
                if (player.getUID() == playerID) {
                    for (SkillCategory skillCategory : player.getSkills()) {
                        if (skillCategory.getCategory().equals(currentSkillCategory)) {
                            for (String notes : skillCategory.getNotes()) {
                                if (notes.toLowerCase().equals(currentlySelectedNote.toLowerCase())) {
                                    skillCategory.getNotes().remove(indexToRemove);
                                    model.exportSystemData();
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

    /**
     * Refreshes the notes being displayed for any new additions or deletions
     */
    private void updateNotes() {
        lstNotes.getItems().clear();
        addNotesToListView(playerID, currentSkillCategory);
    }

    /**
     * Adds the current players notes to the list view
     *
     * @param playerID             The player id
     * @param currentSkillCategory The skill category of the notes
     */
    private void addNotesToListView(int playerID, String currentSkillCategory) {
        for (Player player : model.getPlayers()) {
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

    /**
     * Checks for any duplicate notes
     *
     * @param noteText The note text to check for duplicates
     * @return Returns true if there are duplicate notes. False if none are found
     */
    private boolean checkForDuplicateNote(String noteText) {
        for (Player player : model.getPlayers()) {
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

    /**
     * Opens the user docs upon the user clicking the hyperlink
     */
    @FXML
    private void openUserDocs() {
        UserDocs.openUserDocs();
    }
}
