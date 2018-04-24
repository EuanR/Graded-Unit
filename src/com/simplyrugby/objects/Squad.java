package com.simplyrugby.objects;

import java.util.ArrayList;

/**
 * @author Euan
 */
public class Squad {

    /**
     * The name of the squad
     */
    private String squadName;
    /**
     * Id of players in this squad
     */
    private ArrayList<Integer> playerIDs = new ArrayList<Integer>();
    /**
     * The id of the coach who is coaching the squad
     */
    private int coachID;


    /**
     * Constructor
     *
     * @param squadName The name of the squad
     * @param coachID   The id of the coach who is coaching the squad
     */
    public Squad(String squadName, int coachID) {
        this.squadName = squadName;
        this.coachID = coachID;
    }

    /**
     * Squad name getter
     *
     * @return Name of the squad
     */
    public String getSquadName() {
        return squadName;
    }

    /**
     * Squad name setter
     * @param squadName Name of the squad
     */
    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    /**
     * Adds a new player to the squad
     * @param playerID The id of the player being added
     */
    public void addPlayer(int playerID) {
        playerIDs.add(playerID);
    }

    /**
     * Gets a list of the player ids of the players in the squad
     * @return List of player ids
     */
    public ArrayList<Integer> getPlayers() {
        return playerIDs;
    }

    /**
     * Coach id getter
     * @return Coach id
     */
    public int getCoachID() {
        return coachID;
    }

    /**
     * Coach id setter
     * @param coachID Coach id
     */
    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    /**
     * To string method for the class
     *
     * @return String containing all class data
     */
    @Override
    public String toString() {
        return "Squad{" +
                "squadName='" + squadName + '\'' +
                ", players=" + playerIDs.toString() +
                '}';
    }
}
