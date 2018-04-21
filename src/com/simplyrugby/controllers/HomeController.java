package com.simplyrugby.controllers;

import com.simplyrugby.modals.Modal;
import com.simplyrugby.objects.Player;
import com.simplyrugby.objects.Skill;
import com.simplyrugby.objects.SkillCategory;
import com.simplyrugby.objects.Squad;
import com.simplyrugby.utils.Debug;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomeController {

    @FXML
    private TableColumn playerName;
    @FXML
    private TableColumn playerID;
    @FXML
    private TableView squadPlayers;

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
        getSquad.setResizable(true);
        getSquad.setHeaderText("Please enter the name of the squad you wish to manage");
        getSquad.setTitle("Please enter the name of the squad you wish to manage");
        tempSquadName = getSquad.showAndWait();
        if(tempSquadName.isPresent()) {
            squadName = tempSquadName.get();
        }
        while(!squadExists(squadName)) {
            getSquad.setResizable(true);
            getSquad.setHeaderText("Please enter the name of the squad you wish to manage");
            getSquad.setTitle("Please enter the name of the squad you wish to manage");
            tempSquadName = getSquad.showAndWait();
            if(tempSquadName.isPresent()) {
                squadName = tempSquadName.get();
            }
        }
        int columnIndex = 2;
        try {
            for (SkillCategory skill : modal.getPlayerFromID(modal.getSquads().get(currentSquadIndex).getPlayers().get(0)).getSkills()) {
                TableColumn<List<String>, String> col = new TableColumn<>(skill.getCategory());
                col.setMinWidth(80);
                squadPlayers.getColumns().add(col);
                columnIndex++;
            }
        } catch (IndexOutOfBoundsException e) {
            Debug.log("There are no players in that squad.");
            System.exit(0);

        } catch (Exception e) {
            Debug.log("Unexpected error occurred when creating columns");
            System.exit(0);
        }

        try {
            for(Integer playerID : modal.getSquads().get(currentSquadIndex).getPlayers()) {
                Player currentPlayer = modal.getPlayerFromID(playerID);
                ArrayList<String> values = new ArrayList<String>();
                values.add(Integer.toString(currentPlayer.getUID()));
                values.add(currentPlayer.getFirstname() + " " + currentPlayer.getSurname());
                for(SkillCategory skillCategory : currentPlayer.getSkills()) {
                    for(Skill skill : skillCategory.getSkills()) {
                        values.add(Integer.toString(skill.getSkillRating()));
                    }
                }
                squadPlayers.getItems().addAll(values);
            }
        } catch (IndexOutOfBoundsException e) {
            Debug.log("An unexpected error has occurred when populating table data: IndexOutOfBounds");
            System.exit(0);
        } catch (Exception e) {
            Debug.log("Unexpected error occurred when populating table data");
            System.exit(0);
        }
    }

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    public boolean squadExists(String squadName) {
        int index = 0;
        ArrayList<Squad> squads = modal.getSquads();
        for(Squad squad : squads) {
            if(squad.getSquadName().toLowerCase().equals(squadName.toLowerCase())) {
                currentSquadIndex = index;
                return true;
            }
            index++;
        }
        return false;
    }

}
