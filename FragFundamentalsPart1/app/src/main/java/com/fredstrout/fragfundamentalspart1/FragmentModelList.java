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

public class FragmentModelList extends ListFragment implements InterfaceModels.ModelDataReceiver{
    public static final String TAG = "FragmentMasterList.TAG";
    public static FragmentModelList newInstance() {
        FragmentModelList frag = new FragmentModelList();
        return frag;
    }


    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        InterfaceModels task = new InterfaceModels(this);
        task.execute();
    }

//    @Override
//    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
//        Object o = this.getListAdapter().getItem(_position);
//        String vehicle = o.toString();
//        new AlertDialog.Builder(getActivity())
//                .setTitle("ObjectVehicles")
//                .setMessage(vehicle)
//                .setPositiveButton("OK", null)
//                .show();
//    }

    @Override
    public void DataReceived(ArrayList<ObjectModels> _objectModels) {
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.mContext, android.R.layout.simple_list_item_1, _objectModels);
        setListAdapter(adapter);
    }
}
