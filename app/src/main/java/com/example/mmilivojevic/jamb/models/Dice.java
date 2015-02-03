package com.example.mmilivojevic.jamb.models;

import com.example.mmilivojevic.jamb.utils.Utils;

/**
 * Created by mmilivojevic on 2/3/15.
 */
public class Dice {
    private boolean hold;
    private int currentValue;

    public boolean isHold() {
        return hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }

    public int getCurrentValue() {
        if ((currentValue < 1) && (currentValue > 6)) {
            currentValue = Utils.randomNumber();
        }
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Throw dice and set current value to random number from 1 to 6
     */
    public void play() {
        if(!this.isHold()) {
            this.setCurrentValue(Utils.randomNumber());
        }
    }

    /**
     * Hold dice and skip further throwing
     */
    public void hold() {
        this.setHold(true);
    }

    /**
     * Unhold dice and allow dice throwing
     */
    public  void relise() {
        this.setHold(false);
    }

    /**
     * Comapre two dices based on current value
     * @param o Dice
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Dice) {
            if (this.getCurrentValue() ==  ((Dice) o).getCurrentValue()) {
                return true;
            }
        }
        return false;
    }
}