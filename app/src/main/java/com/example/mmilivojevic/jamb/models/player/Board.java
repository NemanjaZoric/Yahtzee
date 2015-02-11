package com.example.mmilivojevic.jamb.models.player;

import android.util.Log;

import com.example.mmilivojevic.jamb.MainActivity;

import java.io.Serializable;

/**
 * Created by mmilivojevic on 2/10/15.
 */
public class Board implements Serializable {
    public static final int ROWS = 12;
    public static final int COLLUMNS = 3;
    
    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int DOWNUP = 2;

    public static  final int ONE = 0;
    public static  final int TWO = 1;
    public static  final int THREE = 2;
    public static  final int FOUR = 3;
    public static  final int FIVE = 4;
    public static  final int SIX = 5;
    public static  final int MAX = 6;
    public static  final int MIN = 7;
    public static  final int STRAIGHT = 8;
    public static  final int FULL_HAUSE = 9;
    public static  final int POKER = 10;
    public static  final int YAHTZEE = 11;
    
    public static final int NUMBERS = 0;
    public static final int EXTREMS = 1;
    public static final int CATEGORIES = 2;

    private Integer[][] board;

    public Integer[][] getBoard() {
        return board;
    }

    public Board() {
        this.board = new Integer[COLLUMNS][ROWS];
        for (int i = 0; i < COLLUMNS; i++) {
            this.board[i] = new Integer[ROWS];
        }
    }
    
    public Integer[] downColumn() {
        return this.board[DOWN];
    }

    public Integer[] upColumn() {
        return this.board[UP];
    }

    public Integer[] downUpColumn() {
        return this.board[DOWNUP];
    }
    
    /**
     * Write data only once
     */
    public void setValue(Integer value, Integer column, Integer row) {
        if (this.fieldIsAllowed(column, row)) {
            this.board[column][row] = value;
        } else {
            Log.v(MainActivity.TAG, "Result is already written or field is not allowed");
        }
    }

    /**
     * Check filed availability
     * @param column direction
     * @param row category
     * @return
     */
    public boolean fieldIsAllowed(int column, int row) {
        if (this.board[column][row] == null) {
            switch (column) {
                case DOWN:
                    if ((row == ONE) || (this.board[column][row - 1] != null)) {
                        return true;
                    }
                    break;
                case UP:
                    if ((row == YAHTZEE) || (this.board[column][row + 1] != null)) {
                        return true;
                    }
                    break;
                case DOWNUP:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    public Integer getValue(Integer column, Integer row) {
        return board[column][row];
    }
    
    public int sumNumberFields(Integer column) {
        int sum = 0;
        for (int i = ONE; i <= SIX; i++) {
            if (null != board[column][i]) {
                sum += board[column][i];
            }
        }
        return sum;
    }

    public int sumCategoryFields(Integer column) {
        int sum = 0;
        for (int i = STRAIGHT; i <= YAHTZEE; i++) {
            if (null != board[column][i]) {
                sum += board[column][i];
            }
        }
        return sum;
    }

    public int sumExtremFields(Integer column) {
        int max = (board[column][MAX] == null) ? 0 : board[column][MAX];
        int min = (board[column][MIN] == null) ? 0 : board[column][MIN];
        return max - min;
    }
    
    public int sumFinal() {
        int sum = 0;
        sum += sumNumberFields(DOWN) + sumNumberFields(UP) + sumNumberFields(DOWNUP);
        sum += sumExtremFields(DOWN) + sumExtremFields(UP) + sumExtremFields(DOWNUP);
        sum += sumCategoryFields(DOWN) + sumCategoryFields(UP) + sumCategoryFields(DOWNUP);
        return sum;
    }
    
    public boolean isTheBoardFull() {
        for (int i = 0; i < COLLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (null == this.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
