package com.example.mmilivojevic.jamb.models;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.mmilivojevic.jamb.R;

/**
 * Created by mmilivojevic on 2/11/15.
 */
public class DiceImageAdapter extends BaseAdapter {

    private DiceSet diceSet;
    private Context context;


    public DiceImageAdapter(Game game, Context context) {
        this.diceSet = game.getDiceSet();
        this.context = context;
        Log.v("DiceSet", diceSet.toString());
    }

    @Override
    public int getCount() {
        int lnt = diceSet.getResults().length;
        Log.v("DiceSet", "count " + lnt);
        return lnt;
    }

    @Override
    public Object getItem(int position) {
        Log.v("DiceSet", "getItem");
//        return results.get(position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        Log.v("DiceSet", "getItemId");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("DiceSetAdapter", "poz: " + position);
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(130,130));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (diceSet.isHoldDice(position)) {
                imageView.setBackgroundColor(Color.RED);
            }
            
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(this.getImageIdByDiceValue(position));

        return imageView;
    }
    
    private int getImageIdByDiceValue(int position) {
        int diceValue = diceSet.getResults()[position];
        switch (diceValue) {
            case 1: return R.drawable.dice_1;
            case 2: return R.drawable.dice_2;
            case 3: return R.drawable.dice_3;
            case 4: return R.drawable.dice_4;
            case 5: return R.drawable.dice_5;
            case 6: return R.drawable.dice_6;
            default: return 0;
        }
    } 
}
