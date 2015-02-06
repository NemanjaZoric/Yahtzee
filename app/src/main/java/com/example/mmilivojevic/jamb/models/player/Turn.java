package com.example.mmilivojevic.jamb.models.player;

import java.util.ArrayList;

/**
 * Created by mmilivojevic on 2/6/15.
 */
public class Turn {
    private ArrayList dice;
    private Integer score;

    public Turn(ArrayList dice, Integer score) {
        this.dice = dice;
        this.score = score;
    }

    public ArrayList getDice() {
        return dice;
    }

    public void setDice(ArrayList dice) {
        this.dice = dice;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "dice=" + dice +
                ", score=" + score +
                '}';
    }
}
