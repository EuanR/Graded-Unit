package com.simplyrugby.objects;

import java.util.ArrayList;

public class Squad {

    String squadName = "";
    ArrayList<Integer> playerIDs = new ArrayList<Integer>();

    public Squad(String squadName) {
        this.squadName = squadName;
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
