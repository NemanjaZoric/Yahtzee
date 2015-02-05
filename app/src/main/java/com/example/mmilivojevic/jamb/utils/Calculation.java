package com.example.mmilivojevic.jamb.utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mmilivojevic on 2/5/15.
 */
public class Calculation {
    
    public static  final int ONE = 1;
    public static  final int TWO = 2;
    public static  final int THREE = 3;
    public static  final int FOUR = 4;
    public static  final int FIVE = 5;
    public static  final int SIX = 6;
    public static  final int STRAIGHT_LARGE = 40;
    public static  final int STRAIGHT_SMALL = 30;
    public static  final int FULL_HAUSE = 25;
    public static  final int YAHTZEE = 50;
    public static  final int POKER = 0;


    private ArrayList <Integer> originalDiceArray;
    
    private ArrayList <Integer> ones;
    private ArrayList <Integer> twos;
    private ArrayList <Integer> threes;
    private ArrayList <Integer> fours;
    private ArrayList <Integer> fives;
    private ArrayList <Integer> sixes;

			/*goes through each rolled die and puts 1 as a place-holder into the appropriate ArrayList
			* e.g. if the first die value is 1, then 1 is added to the ones ArrayList or
			* if the second die value is 5, then 1 is added to the fives ArrayList*/

    public Calculation(int[] dice) {
        originalDiceArray = new ArrayList<Integer>();
        ones = new ArrayList<Integer>();
        twos = new ArrayList<Integer>();
        threes = new ArrayList<Integer>();
        fours = new ArrayList<Integer>();
        fives = new ArrayList<Integer>();
        sixes = new ArrayList<Integer>();
        for (int i = 0; i < dice.length; i++) {
            originalDiceArray.add(dice[i]);
            
            if (dice[i] == 1) {
                ones.add(1);
            } else if (dice[i] == 2) {
                twos.add(1);
            } else if (dice[i] == 3) {
                threes.add(1);
            } else if (dice[i] == 4) {
                fours.add(1);
            } else if (dice[i] == 5) {
                fives.add(1);
            } else if (dice[i] == 6) {
                sixes.add(1);
            }
        }
    }
    
    public int ones() {
        return this.ones.size() * ONE;
    }

    public int twos() {
        return this.twos.size() * TWO;
    }

    public int threes() {
        return this.threes.size() * THREE;
    }

    public int fours() {
        return this.fours.size() * FOUR;
    }

    public int fives() {
        return this.fives.size() * FIVE;
    }

    public int sixes() {
        return this.sixes.size() * SIX;
    }

    public int straight() {
        // large straigh
        if(ones.size() == 1 && twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1){
            return STRAIGHT_LARGE;
        }
        else if(twos.size() == 1 && threes.size() == 1 && fours.size() == 1 && fives.size() == 1 && sixes.size() == 1) {
            return STRAIGHT_LARGE;
        }

        // small straight
        if(ones.size() >= 1 && twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1) {
            return STRAIGHT_SMALL;
        }
        else if(twos.size() >= 1 && threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1) {
            return STRAIGHT_SMALL;
        }
        else if(threes.size() >= 1 && fours.size() >= 1 && fives.size() >= 1 && sixes.size() >= 1) {
            return STRAIGHT_SMALL;
        }
        
        return 0;
    }

    public int fullHouse() {
        if(ones.size() == 3 || twos.size() == 3 || threes.size() == 3 || fours.size() == 3 || fives.size() == 3 || sixes.size() == 3) {
            if(ones.size() == 2 || twos.size() == 2 || threes.size() == 2 || fours.size() == 2 || fives.size() == 2 || sixes.size() == 2) {
                return FULL_HAUSE;
            }
        }
        
        return 0;
    }

    public int poker() {
        if(ones.size() >= 4 || twos.size() >= 4 || threes.size() >= 4 || fours.size() >= 4 || fives.size() >= 4 || sixes.size() >= 4) {
            return this.sumOfAllDice();
        }
        
        return 0;
    }

    public int jamb() {
        if(ones.size() == 5 || twos.size() == 5 || threes.size() == 5 || fours.size() == 5 || fives.size() == 5 || sixes.size() == 5) {
            return YAHTZEE;
        }

        return 0;
    }

    public int maximum() {
        return this.sumOfAllDice() - Collections.min(this.originalDiceArray);
    }

    public int minimum() {
        return this.sumOfAllDice() - Collections.max(this.originalDiceArray);
    }
    
    public int sumOfAllDice() {
        int sum = 0;
        for (int i: this.originalDiceArray) {
            sum += i;
        }
        return sum;
    }
    
}


