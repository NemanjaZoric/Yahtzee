package com.example.mmilivojevic.jamb.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.mmilivojevic.jamb.BoardActivity;
import com.example.mmilivojevic.jamb.R;

import java.util.Random;

/**
 * Created by mmilivojevic on 2/3/15.
 */
public class Utils {

    /**
     * Return rundom number from 1 to 6
     * @return random number
     */
    public static int randomNumber() {
        Random rand = new Random();
        int randomNum = rand.nextInt(6) + 1;
        return randomNum;
    }

    public static void playDiceSound(Context context) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.dice_sound);
        mediaPlayer.start();
    }
}
