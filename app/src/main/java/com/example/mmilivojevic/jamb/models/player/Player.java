package com.example.mmilivojevic.jamb.models.player;

/**
 * Created by mmilivojevic on 2/6/15.
 */
public class Player {
    
    private Integer id;
    private String name;
    private Turn[] turns;

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
