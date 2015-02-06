package com.example.mmilivojevic.jamb;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mmilivojevic on 2/6/15.
 */
public class Preferences {
    private static final String PREFS_NAME = "edu.self.mm";
    private static final String PREF_NO_PLAYERS = "edu.self.mm.NumberOfPlayers";
    
    private static String getGamePreference(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    
        return  sharedPreferences.getString(key, "1");
    }

    private static void setGamePreference(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static int getNumberOfPlayers(Context context) {
        return Integer.parseInt(Preferences.getGamePreference(context, PREF_NO_PLAYERS));
    }


    public static void setNumberOfPlayers(Context context, Integer numberOfPlayers) {
        Preferences.setGamePreference(context, PREF_NO_PLAYERS, numberOfPlayers.toString());
    }
}
