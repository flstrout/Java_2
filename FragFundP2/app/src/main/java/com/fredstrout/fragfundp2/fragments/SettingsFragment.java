package com.fredstrout.fragfundp2.fragments;

// FragFundP2
// Created by Fred L. Strout
// 10/06/2015
// Java 2 - Project 2

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.fredstrout.fragfundp2.R;
import com.fredstrout.fragfundp2.Utilities.Util;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);


        addPreferencesFromResource(R.xml.settings);
    }

    //  Found this on stackoverflow -> repurposed to work with OnPreferenceChangeListener
//  (http://stackoverflow.com/questions/18301554/android-change-app-theme-on-onclick)
    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        Preference pref = findPreference("PREF_LIST");
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String selValue = (String) newValue.toString();

                switch (selValue){
                    case "Red":
                        Util.changeToTheme(getActivity(), Util.THEME_RED);
                        break;
                    case "Yellow":
                        Util.changeToTheme(getActivity(), Util.THEME_YELLOW);
                        break;
                    default:
                        Util.changeToTheme(getActivity(), Util.THEME_DEFAULT);
                }

//                    Original attempt => too much code/work to set each element individually

//                    TextView mText = (TextView) getActivity().findViewById(R.id.my_text);
//
//                    switch (selValue){
//                        case "Red":
//                            mText.setTextColor(Color.RED);
//                            break;
//                        case "Yellow":
//                            mText.setTextColor(Color.YELLOW);
//                            break;
//                        default:
//                            mText.setTextColor(Color.BLACK);
//                    }
                return false;
            }
        });
    }
}
