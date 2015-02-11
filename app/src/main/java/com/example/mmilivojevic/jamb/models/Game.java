package com.example.mmilivojevic.jamb.models;

import android.content.Context;

import com.example.mmilivojevic.jamb.Preferences;
import com.example.mmilivojevic.jamb.models.player.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mmilivojevic on 2/6/15.
 */
public class Game implements Serializable {
    //    private static Game ourInstance = new Game();
    private Context applicationContext;
    private static DiceSet diceSet;
    private ArrayList <Player> players;
    private int currentPlayer;

//    public static Game getInstance() {
//        return ourInstance;
//    }

    public Game() {
        diceSet = new DiceSet();
        players = new ArrayList<Player>();
        this.setPlayersFromDb();
        currentPlayer =0;
    }

    public Game(Context applicationContext, ArrayList<Player> players) {
        this.applicationContext = applicationContext;
        this.players = players;
    }

    private void setPlayersFromDb() {
        // stub method
        // TODO: add DB support
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

    public Player getPlayerAtIndex(int index) {
        return this.players.get(index);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
