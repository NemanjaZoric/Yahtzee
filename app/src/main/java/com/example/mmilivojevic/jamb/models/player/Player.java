package com.example.mmilivojevic.jamb.models.player;

import java.io.Serializable;

/**
 * Created by mmilivojevic on 2/6/15.
 */
public class Player implements Serializable{
    
    private Integer id;
    private String name;
    private Turn[] turns; // TODO: turns history
    private Board board;

    public Player(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Turn[] getTurns() {
        return turns;
    }

    public void setTurns(Turn[] turns) {
        this.turns = turns;
    }

    @Override
    public String toString() {
        return this.name + " | turns=" + turns.length;
    }
}
