package com.example.mmilivojevic.jamb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mmilivojevic.jamb.models.DiceSet;
import com.example.mmilivojevic.jamb.models.Game;
import com.example.mmilivojevic.jamb.models.player.Player;
import com.example.mmilivojevic.jamb.utils.Calculation;

import java.util.ArrayList;


public class MainActivity extends Activity {

    public static final String TAG = "JambTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        DiceSet diceSet = new DiceSet();
//        Log.v(TAG, diceSet.toString());
//        diceSet.rollDice();
//        Log.v(TAG, diceSet.toString());
//        diceSet.holdDice(1);
//        diceSet.holdDice(5);
//        diceSet.rollDice();
//        Log.v(TAG, diceSet.toString());
//
//        Calculation calculation = new Calculation(diceSet.getResults());
//        Log.v(TAG, "ones: " + String.valueOf(calculation.ones()));
//        Log.v(TAG, "twos: " + String.valueOf(calculation.twos()));
//        Log.v(TAG, "threes: " + String.valueOf(calculation.threes()));
//        Log.v(TAG, "fours: " + String.valueOf(calculation.fours()));
//        Log.v(TAG, "fives: " + String.valueOf(calculation.fives()));
//        Log.v(TAG, "sixes: " + String.valueOf(calculation.sixes()));
//        Log.v(TAG, "max: " + String.valueOf(calculation.maximum()));
//        Log.v(TAG, "min: " + String.valueOf(calculation.minimum()));
//
//        Log.v(TAG, "sthraith: " + String.valueOf(calculation.straight()));
//        Log.v(TAG, "full: " + String.valueOf(calculation.fullHouse()));
//        Log.v(TAG, "poker: " + String.valueOf(calculation.poker()));
//        Log.v(TAG, "jamb: " + String.valueOf(calculation.jamb()));

        
        this.newGame(getApplicationContext());
    }

    private void newGame(Context context) {
       Game game = new Game(context, this.getAllPlayers());
    }

    private ArrayList<Player> getAllPlayers() {
        // TODO: to be implemented
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
