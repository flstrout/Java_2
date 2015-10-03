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
import android.widget.TextView;

import java.util.ArrayList;

import static com.fredstrout.fragfundamentalspart1.R.id.txt_model;

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

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        Object o = this.getListAdapter().getItem(_position);
        String vehicle = o.toString();
        TextView txtModel = (TextView) getView().findViewById(R.id.txt_model);
        txtModel.setText(vehicle);
    }

    @Override
    public void DataReceived(ArrayList<ObjectModels> _objectModels) {
        String selModel = _objectModels.toString();
        new AlertDialog.Builder(getActivity())
                .setTitle("ObjectVehicles")
                .setMessage(selModel)
                .setPositiveButton("OK", null)
                .show();
    }
}
