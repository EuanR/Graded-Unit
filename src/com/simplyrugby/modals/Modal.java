package com.simplyrugby.modals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.simplyrugby.objects.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author Euan
 */
public class Modal {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Squad> squads = new ArrayList<>();
    private ArrayList<Member> coaches = new ArrayList<>();

    public Modal() {
        if (checkForSystemData()) {
            importSystemData();
        } else {
            rebuildSystemData();
        }
    }

    public void rebuildSystemData() {
        ArrayList<String> healthIssues = new ArrayList<String>();
        healthIssues.add("Hayfever");
        players.add(new SeniorPlayer(
                0,
                "John",
                "Davids",
                "123 Fake Street",
                "KY11 PQy",
                "0011223344",
                "21/01/1990",
                "01324 421 512",
                "0123456789",
                "john@email.com",
                "password",
                "Dr Jenkins",
                "01283 765 456",
                "Scrum Half",
                healthIssues,
                "Andrew Davids",
                "01312 421 512"
        ));
        players.add(new SeniorPlayer(
                1,
                "Andrew",
                "Peters",
                "132 Born Street",
                "KY11 PQ1",
                "00111113344",
                "10/05/1990",
                "01324 421 619",
                "0123456781",
                "andrew@email.com",
                "password",
                "Dr Anderson",
                "01283 765 312",
                "Full Back",
                healthIssues,
                "Tim Peters",
                "01312 411 512"
        ));
        players.add(new SeniorPlayer(
                2,
                "Andrew",
                "Breem",
                "123 Madeup Avenue",
                "KY11 PQ0",
                "0912213344",
                "12/03/1995",
                "01324 411 512",
                "0123456719",
                "ian@email.com",
                "password",
                "Dr Peters",
                "01283 729 456",
                "Wing",
                healthIssues,
                "Peter Breem",
                "01312 411 112"
        ));
        players.add(new JuniorPlayer(
                3,
                "Ian",
                "Breem",
                "123 Buchanan Avenue",
                "KY11 PQ0",
                "0912213344",
                "12/03/1995",
                "01324 411 512",
                "0123456719",
                "ian@email.com",
                "password",
                "Dr Peters",
                "01283 729 456",
                "Wing",
                healthIssues,
                "Peter Breem",
                "Dad",
                "123 Buchanan Avenue",
                "01234 512 512",
                "Jessica Breem",
                "Mum",
                "123 Buchanan Avenue",
                "01234 512 512",
                "32 Guild Street",
                true
        ));
        players.add(new JuniorPlayer(
                4,
                "John",
                "Peters",
                "123 Yeoman Avenue",
                "KY11 PQ0",
                "0912213344",
                "12/03/1995",
                "01324 411 512",
                "0123456719",
                "ian@email.com",
                "password",
                "Dr Peters",
                "01283 729 456",
                "Wing",
                healthIssues,
                "Peter Peters",
                "Dad",
                "123 Yeoman Avenue",
                "01234 512 513",
                "Jessica Peters",
                "Mum",
                "123 Yeoman Avenue",
                "01234 512 513",
                "32 Guild Street",
                true
        ));
        players.add(new JuniorPlayer(
                5,
                "Ian",
                "Yellow",
                "123 Buchanan Street",
                "KY11 PQ0",
                "0912213344",
                "12/03/1995",
                "01324 411 512",
                "0123456719",
                "ian@email.com",
                "password",
                "Dr Peters",
                "01283 729 456",
                "Wing",
                healthIssues,
                "Peter Yellow",
                "Dad",
                "123 Buchanan Street",
                "01234 512 512",
                "Jessica Yellow",
                "Mum",
                "123 Buchanan Street",
                "01234 512 512",
                "32 Guild Street",
                true
        ));
        coaches.add(new Member(
                6,
                "Harlod",
                "Anderson",
                "42 Fake Street",
                "PYT 0123",
                "0912213344",
                "01/02/1990",
                "01234 412 124",
                "081212341",
                "harold@emalprovider.com",
                "password"
        ));
        coaches.add(new Member(
                7,
                "Pete",
                "Henderson",
                "42 Milk Street",
                "AYX 0123",
                "0112213344",
                "01/05/1990",
                "01234 500 124",
                "081212300",
                "petehenderson@email.com",
                "password"
        ));
        squads.add(new Squad(
                "Senior Team",
                6
        ));
        squads.add(new Squad(
                "Junior Team",
                7
        ));
        squads.get(0).addPlayer(players.get(0).getUID());
        squads.get(0).addPlayer(players.get(1).getUID());
        squads.get(0).addPlayer(players.get(2).getUID());
        squads.get(1).addPlayer(players.get(3).getUID());
        squads.get(1).addPlayer(players.get(4).getUID());
        squads.get(1).addPlayer(players.get(5).getUID());
        for (Player player : players) {
            player.addSkillCategory("Passing");
            player.addSkillToCategory("Passing", "Standard", "0");
            player.addSkillToCategory("Passing", "Spin", "0");
            player.addSkillToCategory("Passing", "Pop", "0");
            player.addSkillCategory("Tackling");
            player.addSkillToCategory("Tackling", "Front", "0");
            player.addSkillToCategory("Tackling", "Rear", "0");
            player.addSkillToCategory("Tackling", "Side", "0");
            player.addSkillToCategory("Tackling", "Scrabble", "0");
            player.addSkillCategory("Kicking");
            player.addSkillToCategory("Kicking", "Drop", "0");
            player.addSkillToCategory("Kicking", "Punt", "0");
            player.addSkillToCategory("Kicking", "Grubber", "0");
            player.addSkillToCategory("Kicking", "Goal", "0");
            player.getSkills().get(0).addNotes("Good at passing");
        }
        exportSystemData();
    }

    public void importSystemData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type playerType = new TypeToken<ArrayList<Player>>() {
        }.getType();

        try {
            Reader reader = new FileReader("data/players.json");
            //players = gson.fromJson(reader, new TypeToken<ArrayList<Player>>(){}.getType());
            players = new Gson().fromJson(reader, playerType);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        try {
            Reader reader = new FileReader("data/squads.json");
            squads = gson.fromJson(reader, new TypeToken<ArrayList<Squad>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        try {
            Reader reader = new FileReader("data/coaches.json");
            coaches = gson.fromJson(reader, new TypeToken<ArrayList<Member>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void exportSystemData() {
        String json = "";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (!new File("data/").exists()) {
            new File("data/").mkdirs();
        }
        try (Writer writer = new FileWriter("data/players.json")) {
            gson.toJson(players, writer);
        } catch (IOException e) {
            System.out.println("Error writing json to file");
        }

        try (Writer writer = new FileWriter("data/squads.json")) {
            gson.toJson(squads, writer);
        } catch (IOException e) {
            System.out.println("Error writing json to file");
        }

        try (Writer writer = new FileWriter("data/coaches.json")) {
            gson.toJson(coaches, writer);
        } catch (IOException e) {
            System.out.println("Error writing json to file");
        }
    }

    public boolean checkForSystemData() {
        File playersData = new File("data/players.json");
        File squadsData = new File("data/squads.json");
        File coachData = new File("data/coaches.json");
        if (playersData.exists() && squadsData.exists() && coachData.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Squad> getSquads() {
        return squads;
    }

    public ArrayList<Member> getCoaches() {
        return coaches;
    }

}
