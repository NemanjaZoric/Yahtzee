package com.example.mmilivojevic.jamb;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.mmilivojevic.jamb.models.DiceImageAdapter;
import com.example.mmilivojevic.jamb.models.Game;
import com.example.mmilivojevic.jamb.utils.Calculation;
import com.squareup.seismic.ShakeDetector;


public class BoardActivity extends Activity implements ShakeDetector.Listener{
    
    private Game game;
    GridView gridView;
    private ShakeDetector shakeDetector;
    private int rollCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setContentView(R.layout.activity_board);
            this.game = (Game)extras.getSerializable(MainActivity.FIRST_ACTIVITY_GAME);
        }

        setTitle(game.getPlayerAtIndex(game.getCurrentPlayer()).getName());
        
        
        // Shaking
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        this.shakeDetector = new ShakeDetector(this);
        this.shakeDetector.start(sensorManager);
        
        
        // Dice GridView
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
    public void hearShake() {
        rollBtn(null);
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
        }
    }
    
    public void onesDown(View view) {
//        Button button = (Button) findViewById(R.id.btnOnesDown);
        int[] dice = Game.getDiceSet().getResults();
        Calculation calculation = new Calculation(dice);
        int result = calculation.ones();
        Log.v(MainActivity.TAG, "res: " + result);
        ((Button) view).setText(result + "");

        // TODO set Player board data
    }


}
