package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MasterListFragment extends ListFragment{
    public static final String TAG = "MasterListFragment.TAG";
    public static MasterListFragment newInstance() {
        MasterListFragment frag = new MasterListFragment();
        return frag;

    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        ArrayList<String> vehicles = new ArrayList<String>();
        vehicles.add("Aston Martin");
        vehicles.add("Buick");
        vehicles.add("Lamborghini");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, vehicles);
        setListAdapter(adapter);
    }
    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        String vehicle = (String)_l.getItemAtPosition(_position);
        new AlertDialog.Builder(getActivity())
                .setTitle("Vehicle")
                .setMessage(vehicle)
                .setPositiveButton("OK", null)
                .show();
    }

}
