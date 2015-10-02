package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MasterListFragment extends ListFragment implements VehicleInterface.VehicleDataReceiver{
    public static final String TAG = "MasterListFragment.TAG";
    public static MasterListFragment newInstance() {
        MasterListFragment frag = new MasterListFragment();
        return frag;
    }


    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        VehicleInterface task = new VehicleInterface(this);
        task.execute();
    }

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        Object o = this.getListAdapter().getItem(_position);
        String vehicle = o.toString();
        new AlertDialog.Builder(getActivity())
                .setTitle("Vehicle")
                .setMessage(vehicle)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void DataReceived(ArrayList<Vehicle> vehicles) {
        VehicleAdapter adapter = new VehicleAdapter(MainActivity.mContext, android.R.layout.simple_list_item_1, vehicles);
        setListAdapter(adapter);
    }
}
