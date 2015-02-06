package com.example.mmilivojevic.jamb.models;

import android.content.Context;

import com.example.mmilivojevic.jamb.Preferences;
import com.example.mmilivojevic.jamb.models.player.Player;

import java.util.ArrayList;

/**
 * Created by mmilivojevic on 2/6/15.
 */
public class Game {
//    private static Game ourInstance = new Game();
    private Context applicationContext;
    private static DiceSet diceSet;
    private ArrayList <Player> players;

//    public static Game getInstance() {
//        return ourInstance;
//    }

    private Game() {
        diceSet = new DiceSet();
        players = new ArrayList<Player>();
    }

    public Game(Context applicationContext, ArrayList<Player> players) {
        this.applicationContext = applicationContext;
        this.players = players;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static DiceSet getDiceSet() {
        return diceSet;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}
