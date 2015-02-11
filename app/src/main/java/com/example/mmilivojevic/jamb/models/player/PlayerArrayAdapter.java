package com.example.mmilivojevic.jamb.models.player;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmilivojevic on 2/12/15.
 */
public class PlayerArrayAdapter  extends ArrayAdapter<Player> {
    
    private List<Player> players;

    public PlayerArrayAdapter(Context context, int resource, int textViewResourceId, List<Player> players) {
        super(context, resource, textViewResourceId, players);
        this.players = players;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
        
        // TODO: implement this
    }
}
