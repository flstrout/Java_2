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

//        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
//        vehicles.add(new Vehicle("Aston Martin", "Model 1"));
//        vehicles.add(new Vehicle("Buick", "Model 2"));
//        vehicles.add(new Vehicle("Lamborghini", "Model 3"));

//        VehicleAdapter adapter = new VehicleAdapter(MainActivity.mContext, android.R.layout.simple_list_item_1, vehicles);
//        setListAdapter(adapter);
    }
//    @Override
//    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
//        String vehicle = (String)_l.getItemAtPosition(_position);
//        new AlertDialog.Builder(getActivity())
//                .setTitle("Vehicle")
//                .setMessage(vehicle)
//                .setPositiveButton("OK", null)
//                .show();
//    }

    @Override
    public void DataReceived(ArrayList<Vehicle> vehicles) {
        VehicleAdapter adapter = new VehicleAdapter(MainActivity.mContext, android.R.layout.simple_list_item_1, vehicles);
        setListAdapter(adapter);
    }


    private class ModelDataTask extends AsyncTask<Void, Void, ArrayList<Vehicle>> {

        @Override
        protected ArrayList<Vehicle> doInBackground(Void... voids) {
            String mData = VehicleUtil.getVehicleData();
            Toast data = Toast.makeText(MainActivity.mContext, mData.toString(),Toast.LENGTH_SHORT);
            data.show();
            ArrayList<Vehicle> vehicles = VehicleUtil.parseVehicleData(mData);
            return vehicles;
        }

        @Override
        protected void onPostExecute(ArrayList<Vehicle> vehicles) {
            super.onPostExecute(vehicles);

            VehicleAdapter adapter = new VehicleAdapter(MainActivity.mContext, android.R.layout.simple_list_item_1, vehicles);
            setListAdapter(adapter);
        }
    }

}
