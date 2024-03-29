package com.fredstrout.fragfundp2;

// FragFundP2
// Created by Fred L. Strout
// 10/06/2015
// Java 2 - Project 2

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.fredstrout.fragfundp2.Utilities.Util;
import com.fredstrout.fragfundp2.fragments.FragDisplayDetail;
import com.fredstrout.fragfundp2.fragments.FragVehicleMake;
import com.fredstrout.fragfundp2.fragments.SettingsFragment;

public class MainActivity extends Activity implements FragVehicleMake.OnRowSelectedClickListener{

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mContext.setTheme(R.style.FirstTheme);
        Util.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);


        Toast newToast = Toast.makeText(this, Environment.getExternalStorageState() + ", " + Environment.getExternalStorageDirectory(), Toast.LENGTH_LONG);
        newToast.show();

        if (savedInstanceState == null){
            FragVehicleMake frag = FragVehicleMake.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.list_container, frag, FragVehicleMake.TAG)
                    .commit();
        }

        SettingsFragment frag1 = new SettingsFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.pref_container, frag1)
                .commit();
    }

    @Override
    public void displayText(String text) {

        FragDisplayDetail frag = (FragDisplayDetail) getFragmentManager().findFragmentByTag(FragDisplayDetail.TAG);

        if (frag == null){
            frag = FragDisplayDetail.newInstance(text);
            getFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, frag, FragDisplayDetail.TAG)
                    .commit();

        } else {
            frag.setDisplayText(text);
        };
    }

}
