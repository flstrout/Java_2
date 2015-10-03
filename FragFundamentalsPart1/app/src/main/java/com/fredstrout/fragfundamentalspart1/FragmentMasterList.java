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
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentMasterList extends ListFragment implements InterfaceVehicles.VehicleDataReceiver{
    public static final String TAG = "FragmentMasterList.TAG";

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        if(netCheck()){
            InterfaceVehicles task = new InterfaceVehicles(this);
            task.execute();
        } else {
            Toast noNetwork = Toast.makeText(MainActivity.mContext, "Network Not Available!", Toast.LENGTH_SHORT);
            noNetwork.show();
        }
    }

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        Object o = this.getListAdapter().getItem(_position);
        String vehicle = o.toString();

//        TextView txtModel= (TextView) onCreateView.findViewById(R.id.txt_model);
//        txtModel.setText(vehicle);
        new AlertDialog.Builder(getActivity())
                .setTitle("ObjectVehicles")
                .setMessage(vehicle)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void DataReceived(ArrayList<ObjectVehicles> objectVehicles) {
        AdapterVehicles adapter = new AdapterVehicles(MainActivity.mContext, android.R.layout.simple_list_item_1, objectVehicles);
        setListAdapter(adapter);
    }
    public boolean netCheck() {

        ConnectivityManager mgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if(mgr != null){
            NetworkInfo info = mgr.getActiveNetworkInfo();
            if (info != null && info.isAvailable()){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}