package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VehicleAdapter extends BaseAdapter {

    public final static int ID_CONSTANT = 0x00010001;

    Context mContext;
    ArrayList<Vehicle> mVehicles;

    public VehicleAdapter(Context _c, int simple_list_item_1, ArrayList<Vehicle> _vehicles){
        mContext = _c;
        mVehicles = _vehicles;
    }

    @Override
    public int getCount() {

        if (mVehicles == null) {
            return 0;
        }

        return mVehicles.size();
    }

    @Override
    public Vehicle getItem(int position) {

        if (mVehicles == null || position < 0 || position >= mVehicles.size()) {
            return null;
        }

        return mVehicles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        Vehicle vehicle = getItem(position);

        if (vehicle != null) {

            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(vehicle.getMake());

            tv = (TextView) convertView.findViewById(android.R.id.text2);
            tv.setText(vehicle.getModel());
        }

        return convertView;
    }
}
