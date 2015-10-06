package com.fredstrout.fragfundp2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.fredstrout.fragfundp2.fragments.FragDisplayDetail;
import com.fredstrout.fragfundp2.fragments.FragVehicleMake;

public class MainActivity extends Activity implements FragVehicleMake.OnRowSelectedClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            FragVehicleMake frag = FragVehicleMake.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.list_container, frag, FragVehicleMake.TAG)
                    .commit();
        }

    }

    @Override
    public void displayText(String text) {

        Log.i("Test", "Item Selected: " + text);
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
