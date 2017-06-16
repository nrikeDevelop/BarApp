package com.barapp.susy.barapp.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by susy on 16/06/17.
 */

public class Preferences {

    static String PREFERNECES_NAME = "PREFERENCES_BAR";

    public static void likeBar(Context context, boolean like){
        SharedPreferences preferences = context.getSharedPreferences(PREFERNECES_NAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("bar_like",like);
        editor.commit();
    }

    public static boolean getLikeBar(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFERNECES_NAME,context.MODE_PRIVATE);
        return preferences.getBoolean("bar_like",false);
    }

}
