package com.simplyrugby.controllers;

import com.simplyrugby.modals.Modal;
import javafx.fxml.FXML;

/**
 * @author Euan
 */
public class SkillsMenuController {

    Modal modal;
    private int playerID;

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    @FXML
    public void init(int playerID) {
        this.playerID = playerID;
    }
}
