package com.example.mmilivojevic.jamb.models;

/**
 * Created by mmilivojevic on 2/3/15.
 */
public class DiceSet {

    private static DiceSet ourInstance = new DiceSet();

    public static DiceSet getInstance() {
        return ourInstance;
    }

    private static final int NUMBER_OF_DICE = 6;
    private Dice[] diceSet = new Dice[NUMBER_OF_DICE];

    DiceSet() {
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            diceSet[i] = new Dice();
        }
    }

    /**
     * Throw all dice
     */
    public void rollDice() {
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            diceSet[i].play();
        }
    }

    /**
     * Get dice results after throwing
     * @return array of dice current values
     */
    public int[] getResults() {
        int[] results = new int[NUMBER_OF_DICE];
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            results[i] = diceSet[i].getCurrentValue();
        }
        return  results;
    }

    /**
     * Hold dice at index
     * @param index
     */
    public void holdDice(int index) {
        diceSet[index].hold();
    }
    
    /**
     * Realise  dice at index
     * @param index
     */
    public void realiseDice(int index) {
        diceSet[index].realise();
    }
    
    public boolean isHoldDice(int index) {
        return diceSet[index].isHold();
    }

    @Override
    public String toString() {
        String str = new String();
        for (Dice d: diceSet) {
            str += d.toString() + " | ";
        }
        return  str;
    }
}
