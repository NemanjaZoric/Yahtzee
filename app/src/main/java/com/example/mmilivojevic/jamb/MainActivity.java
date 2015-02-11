package com.example.mmilivojevic.jamb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mmilivojevic.jamb.models.Game;
import com.example.mmilivojevic.jamb.models.player.Player;

import java.util.ArrayList;


public class MainActivity extends Activity {

    public static final String TAG = "JambTAG";
    public static final String FIRST_ACTIVITY_GAME = "com.example.mmilivojevic.jamb.players";
    
//    private List<Player> players = new ArrayList<>();
    
    private Game game = new Game();


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
    
    public void playTheGame(View view) {
        Intent boardIntent = new Intent(this, BoardActivity.class);
        // TODO: place players in extras
         boardIntent.putExtra(FIRST_ACTIVITY_GAME, this.game);
        if (game.getPlayers().size() != 0) {
            startActivity(boardIntent);
        } else {
            Toast.makeText(this, "Add some players", Toast.LENGTH_LONG).show();
        }
    }
    
    public void addNewPlayer(View view) {
        EditText playerName = (EditText) findViewById(R.id.etNewPlayerName);
        playerName.clearComposingText();
        if (!playerName.getText().toString().equals("")) {
            this.game.addPlayer(new Player(playerName.getText().toString()));
        }
        
        playerName.setText("");
        this.printPlayers();
    }

    private void printPlayers() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutPlayers);
        linearLayout.removeAllViewsInLayout();
        for (Player player : this.game.getPlayers()) {
            TextView tempPlayerView = new TextView(this);
            tempPlayerView.setText(player.getName());
            tempPlayerView.setTextSize(20);
            tempPlayerView.setPadding(0,10,0,10);
            
            linearLayout.addView(tempPlayerView);
        }
    }
}
