package com.fredstrout.fragfundp2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements FragVehicleMake.OnRowSelectedClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager mgr = getFragmentManager();
//        FragmentTransaction trans = mgr.beginTransaction();

        if (savedInstanceState == null){
            FragVehicleMake frag = FragVehicleMake.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, frag, FragVehicleMake.TAG)
                    .commit();
        }

    }

    @Override
    public void displayText(String text) {

        Log.i("Test", "Item Selected: " + text);
    }
}
