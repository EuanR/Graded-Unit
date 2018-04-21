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

public class Search {

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
