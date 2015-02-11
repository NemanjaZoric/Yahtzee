package com.example.mmilivojevic.jamb.models;

import android.content.Context;

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
    private int currentPlayerIndex;

//    public static Game getInstance() {
//        return ourInstance;
//    }

    public Game() {
        diceSet = new DiceSet();
        players = new ArrayList<Player>();
        this.setPlayersFromDb();
        currentPlayerIndex =0;
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

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Player getCurrentPlayer() {
        return this.players.get(currentPlayerIndex);
        
    }
    
    public void setCurrentPlayerIndex(int currentPlayer) {
        this.currentPlayerIndex = currentPlayer;
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    public boolean isTheEndOfGame() {
        for (Player player : this.players) {
            if (!player.getBoard().isTheBoardFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Winner of the game
     *
     * @return winner if applicable, otherwise return null
     */
    public Player getWinner() {
        if (this.players.size() < 1) {
            return null;
        }
        
        Player winner = this.players.get(0);
        if (this.isTheEndOfGame()) {
            for (int i = 0; i < this.players.size(); i++) {
                if (this.players.get(i).getBoard().sumFinal() > winner.getBoard().sumFinal()) {
                    winner = this.players.get(i);
                }
            }
            return winner;
        }
        return null;
    }
}
