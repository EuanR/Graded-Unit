package com.simplyrugby.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.simplyrugby.exceptions.PlayerNotFoundException;
import com.simplyrugby.objects.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Euan
 */
public class Search {

    /**
     * Returns a Player object from a given player ID
     * <pre>
     *     {@code
     *      Player tempPlayer = Search.getPlayerFromID(102);
     *     }
     * </pre>
     *
     * @param playerID The ID of the player
     * @return An instance of the Player object from the ID
     * @throws PlayerNotFoundException Throws if there is no player matching the given ID, or if the player is not found
     */
    public static Player getPlayerFromID(int playerID) throws PlayerNotFoundException {
        ArrayList<Player> tempPlayers = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        boolean found = false;
        Type playerType = new TypeToken<ArrayList<Player>>() {
        }.getType();
        try {
            Reader reader = new FileReader("data/players.json");
            tempPlayers = new Gson().fromJson(reader, playerType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        for (Player player : tempPlayers) {
            if (player.getUID() == playerID) {
                found = true;
                return player;
            }
        }
        if (!found) {
            throw new PlayerNotFoundException();
        }
        return null;
    }
}
