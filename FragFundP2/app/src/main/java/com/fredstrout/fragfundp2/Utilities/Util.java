package com.fredstrout.fragfundp2.Utilities;

// FragFundP2
// Created by Fred L. Strout
// 10/06/2015
// Java 2 - Project 2

import android.app.Activity;
import android.content.Intent;

import com.fredstrout.fragfundp2.R;

public class Util {
    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_RED = 1;
    public final static int THEME_YELLOW = 2;
    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.FirstTheme);
                break;
            case THEME_RED:
                activity.setTheme(R.style.SecondTheme);
                break;
            case THEME_YELLOW:
                activity.setTheme(R.style.ThirdTheme);
                break;
        }
    }
}
