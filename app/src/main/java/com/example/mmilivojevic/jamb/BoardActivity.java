/** 
  * TODO:
  * 1. Recognize end of game! ± done -> create leaderboard activity
  * 2. Shaking Sensor - AI
  * 3. Save players to DB
  * 4.
  * 5.
  */
package com.example.mmilivojevic.jamb;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mmilivojevic.jamb.models.DiceImageAdapter;
import com.example.mmilivojevic.jamb.models.Game;
import com.example.mmilivojevic.jamb.models.player.Board;
import com.example.mmilivojevic.jamb.models.player.Player;
import com.example.mmilivojevic.jamb.utils.Calculation;
import com.example.mmilivojevic.jamb.utils.Utils;
import com.squareup.seismic.ShakeDetector;


public class BoardActivity extends Activity implements ShakeDetector.Listener{
    
    private Game game;
    private GridView gridView;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;
    private int rollCount = 0;
    private boolean turnIsFinish = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setContentView(R.layout.activity_board);
            this.game = (Game)extras.getSerializable(MainActivity.FIRST_ACTIVITY_GAME);
        }

        setTitle(game.getPlayerAtIndex(game.getCurrentPlayerIndex()).getName());
                
        // Shaking
        
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.shakeDetector = new ShakeDetector(this);
        this.shakeDetector.start(sensorManager);
        
        //nextPlayer
        this.nextPlayerTurn();

    }

    @Override
    public void hearShake() {
        Utils.playDiceSound(this);
        rollBtn(null);
    }

    
    private void setupDiceView() {
        gridView = (GridView) findViewById(R.id.gridViewDice);
        gridView.setAdapter(new DiceImageAdapter(this.game, this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Game.getDiceSet().isHoldDice(position)) {
                    Game.getDiceSet().realiseDice(position);
                    view.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    Game.getDiceSet().holdDice(position);
                    view.setBackgroundColor(Color.RED);
                }
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void rollBtn(View view) {
        this.rollCount++;
        if (rollCount < 3) {
            game.getDiceSet().rollDice();
            gridView.setAdapter(new DiceImageAdapter(game, this));
        } else {
            this.shakeDetector.stop();
        }
    }

    public void nextPlayer(View view) {
        if (this.game.isTheEndOfGame()) {
            // TODO: move to rating list
            // finish game
        }
        
        if (this.turnIsFinish) {
            game.nextPlayer();
            this.nextPlayerTurn();
            this.scrollToTop();
        } else {
            Toast.makeText(this, "You mast sign result!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Scroll to the top of screen
     */
    private void scrollToTop() {
        ScrollView scrollView = (ScrollView) findViewById(R.id.gameScrollView);
        scrollView.fullScroll(ScrollView.FOCUS_UP);
    }

    private void nextPlayerTurn() {
        this.turnIsFinish = false;
        
        // populate board
        this.populateBoard();
        
        // reset shaker
        shakeDetector.start(this.sensorManager);
        
        // reset roll count
        this.rollCount = 0;
        
        // setTitle
        setTitle(game.getCurrentPlayer().getName());
        
        // reset DiceSet
        this.game.getDiceSet().realiseAll();
        this.game.getDiceSet().rollDice();
        
        // refreshDice
        this.setupDiceView();
    }
    
    private void populateBoard() {

        Player currentPlayer = this.game.getCurrentPlayer();
        Integer[][] buttons = new Integer[Board.COLLUMNS][Board.ROWS];
        
        buttons[Board.DOWN][Board.ONE] = R.id.btnOnesDown;
        buttons[Board.DOWN][Board.TWO] = R.id.btnTwosDown;
        buttons[Board.DOWN][Board.THREE] = R.id.btnThreesDown;
        buttons[Board.DOWN][Board.FOUR] = R.id.btnFoursDown;
        buttons[Board.DOWN][Board.FIVE] = R.id.btnFivesDown;
        buttons[Board.DOWN][Board.SIX] = R.id.btnSixesDown;
        buttons[Board.DOWN][Board.MAX] = R.id.btnMaxDown;
        buttons[Board.DOWN][Board.MIN] = R.id.btnMinDown;
        buttons[Board.DOWN][Board.STRAIGHT] = R.id.btnStraightDown;
        buttons[Board.DOWN][Board.FULL_HAUSE] = R.id.btnFullDown;
        buttons[Board.DOWN][Board.POKER] = R.id.btnPokerDown;
        buttons[Board.DOWN][Board.YAHTZEE] = R.id.btnYahtzeeDown;

        buttons[Board.UP][Board.ONE] = R.id.btnOnesUp;
        buttons[Board.UP][Board.TWO] = R.id.btnTwosUp;
        buttons[Board.UP][Board.THREE] = R.id.btnThreesUp;
        buttons[Board.UP][Board.FOUR] = R.id.btnFoursUp;
        buttons[Board.UP][Board.FIVE] = R.id.btnFivesUp;
        buttons[Board.UP][Board.SIX] = R.id.btnSixesUp;
        buttons[Board.UP][Board.MAX] = R.id.btnMaxUp;
        buttons[Board.UP][Board.MIN] = R.id.btnMinUp;
        buttons[Board.UP][Board.STRAIGHT] = R.id.btnStraightUp;
        buttons[Board.UP][Board.FULL_HAUSE] = R.id.btnFullUp;
        buttons[Board.UP][Board.POKER] = R.id.btnPokerUp;
        buttons[Board.UP][Board.YAHTZEE] = R.id.btnYahtzeeUp;

        buttons[Board.DOWNUP][Board.ONE] = R.id.btnOnesDownUp;
        buttons[Board.DOWNUP][Board.TWO] = R.id.btnTwosDownUp;
        buttons[Board.DOWNUP][Board.THREE] = R.id.btnThreesDownUp;
        buttons[Board.DOWNUP][Board.FOUR] = R.id.btnFoursDownUp;
        buttons[Board.DOWNUP][Board.FIVE] = R.id.btnFivesDownUp;
        buttons[Board.DOWNUP][Board.SIX] = R.id.btnSixesDownUp;
        buttons[Board.DOWNUP][Board.MAX] = R.id.btnMaxDownUp;
        buttons[Board.DOWNUP][Board.MIN] = R.id.btnMinDownUp;
        buttons[Board.DOWNUP][Board.STRAIGHT] = R.id.btnStraightDownUp;
        buttons[Board.DOWNUP][Board.FULL_HAUSE] = R.id.btnFullDownUp;
        buttons[Board.DOWNUP][Board.POKER] = R.id.btnPokerDownUp;
        buttons[Board.DOWNUP][Board.YAHTZEE] = R.id.btnYahtzeeDownUp;

        Integer[][] sums = new Integer[Board.COLLUMNS][3];
        sums[Board.DOWN][Board.NUMBERS] = R.id.textViewDownSum1;
        sums[Board.UP][Board.NUMBERS] = R.id.textViewUpSum1;
        sums[Board.DOWNUP][Board.NUMBERS] = R.id.textViewDownUpSum1;

        sums[Board.DOWN][Board.EXTREMS] = R.id.textViewDownSum2;
        sums[Board.UP][Board.EXTREMS] = R.id.textViewUpSum2;
        sums[Board.DOWNUP][Board.EXTREMS] = R.id.textViewDownUpSum2;

        sums[Board.DOWN][Board.CATEGORIES] = R.id.textViewDownSum3;
        sums[Board.UP][Board.CATEGORIES] = R.id.textViewUpSum3;
        sums[Board.DOWNUP][Board.CATEGORIES] = R.id.textViewDownUpSum3;

        // populate fields
        for (int i = 0; i < Board.COLLUMNS; i++) {
            for (int j = 0; j < Board.ROWS; j++) {
                Button tmpBtn = (Button) findViewById(buttons[i][j]);
                if (null != currentPlayer.getBoard().getValue(i, j)) {
                    tmpBtn.setText(String.valueOf(currentPlayer.getBoard().getValue(i, j)));
                    tmpBtn.setEnabled(false);
                } else {
                    tmpBtn.setText("");
                    tmpBtn.setEnabled(true);
                }
                if (!currentPlayer.getBoard().fieldIsAllowed(i, j)) {
                    tmpBtn.setEnabled(false);
                }
            }
        }
        
        // populate sums
        TextView tmpTV = (TextView) findViewById(R.id.textViewDownUpSumFinal);
        tmpTV.setText(String.valueOf(currentPlayer.getBoard().sumFinal()));

        for (int column = 0; column < Board.COLLUMNS; column++) {
            TextView tmpTextViewNum = (TextView) findViewById(sums[column][Board.NUMBERS]);
            TextView tmpTextViewExt = (TextView) findViewById(sums[column][Board.EXTREMS]);
            TextView tmpTextViewCat = (TextView) findViewById(sums[column][Board.CATEGORIES]);
            
            tmpTextViewNum.setText(String.valueOf(currentPlayer.getBoard().sumNumberFields(column)));
            tmpTextViewExt.setText(String.valueOf(currentPlayer.getBoard().sumExtremFields(column)));
            tmpTextViewCat.setText(String.valueOf(currentPlayer.getBoard().sumCategoryFields(column)));
        }
    }


    private void finishBtnClick(int result, int column, int row) {
        if (!turnIsFinish) {
            // save result
            this.game.getCurrentPlayer().getBoard().setValue(result, column, row);

            // set Player board data
            this.populateBoard();

            this.turnIsFinish = true;
        }
    }
    
    /* numbers */
    public void onesDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.ones(), Board.DOWN, Board.ONE);
    }
    
    public void twosDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.twos(), Board.DOWN, Board.TWO);
    }

    public void threesDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.threes(), Board.DOWN, Board.THREE);
    }

    public void foursDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fours(), Board.DOWN, Board.FOUR);
    }

    public void fivesDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fives(), Board.DOWN, Board.FIVE);
    }

    public void sixesDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.sixes(), Board.DOWN, Board.SIX);
    }

    public void onesUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.ones(), Board.UP, Board.ONE);
    }

    public void twosUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.twos(), Board.UP, Board.TWO);
    }

    public void threesUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.threes(), Board.UP, Board.THREE);
    }

    public void foursUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fours(), Board.UP, Board.FOUR);
    }

    public void fivesUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fives(), Board.UP, Board.FIVE);
    }

    public void sixesUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.sixes(), Board.UP, Board.SIX);
    }

    public void onesDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.ones(), Board.DOWNUP, Board.ONE);
    }

    public void twosDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.twos(), Board.DOWNUP, Board.TWO);
    }

    public void threesDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.threes(), Board.DOWNUP, Board.THREE);
    }

    public void foursDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fours(), Board.DOWNUP, Board.FOUR);
    }

    public void fivesDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fives(), Board.DOWNUP, Board.FIVE);
    }

    public void sixesDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.sixes(), Board.DOWNUP, Board.SIX);
    }    
    /* END numbers */
    
    /* extrems */
    public void maxDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.maximum(), Board.DOWN, Board.MAX);
    }

    public void minDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.minimum(), Board.DOWN, Board.MIN);
    }

    public void maxUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.maximum(), Board.UP, Board.MAX);
    }

    public void minUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.minimum(), Board.UP, Board.MIN);
    }

    public void maxDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.maximum(), Board.DOWNUP, Board.MAX);
    }

    public void minDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.minimum(), Board.DOWNUP, Board.MIN);
    }
    /* END extrems */
    
    /* categories */
    public void straightDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.straight(), Board.DOWN, Board.STRAIGHT);
    }

    public void fullDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fullHouse(), Board.DOWN, Board.FULL_HAUSE);
    }

    public void pokerDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.poker(), Board.DOWN, Board.POKER);
    }

    public void yahtzeeDown(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.jamb(), Board.DOWN, Board.YAHTZEE);
    }

    public void straightUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.straight(), Board.UP, Board.STRAIGHT);
    }

    public void fullUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fullHouse(), Board.UP, Board.FULL_HAUSE);
    }

    public void pokerUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.poker(), Board.UP, Board.POKER);
    }

    public void yahtzeeUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.jamb(), Board.UP, Board.YAHTZEE);
    }

    public void straightDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.straight(), Board.DOWNUP, Board.STRAIGHT);
    }

    public void fullDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.fullHouse(), Board.DOWNUP, Board.FULL_HAUSE);
    }

    public void pokerDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.poker(), Board.DOWNUP, Board.POKER);
    }

    public void yahtzeeDownUp(View view) {
        Calculation calculation = new Calculation(Game.getDiceSet().getResults());
        this.finishBtnClick(calculation.jamb(), Board.DOWNUP, Board.YAHTZEE);
    }
    /* END categories */
}
