package com.simplyrugby.objects;

import java.util.ArrayList;

/**
 * @author Euan
 */
public class Squad {

    private String squadName = "";
    private ArrayList<Integer> playerIDs = new ArrayList<Integer>();
    private int coachID;


    public Squad(String squadName, int coachID) {
        this.squadName = squadName;
        this.coachID = coachID;
    }

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public void addPlayer(int playerID) {
        playerIDs.add(playerID);
    }

    public ArrayList<Integer> getPlayers() {
        return playerIDs;
    }

    @Override
    public String toString() {
        return "Squad{" +
                "squadName='" + squadName + '\'' +
                ", players=" + playerIDs.toString() +
                '}';
    }
}
