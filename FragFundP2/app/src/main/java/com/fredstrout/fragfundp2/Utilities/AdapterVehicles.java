package com.fredstrout.fragfundp2.Utilities;

// FragFundP2
// Created by Fred L. Strout
// 10/06/2015
// Java 2 - Project 2

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterVehicles extends BaseAdapter {

    public final static int ID_CONSTANT = 0x00010001;

    Context mContext;
    ArrayList<ObjectVehicles> mObjectVehicles;

    public AdapterVehicles(Context _c, int simple_list_item_1, ArrayList<ObjectVehicles> _objectVehicles){
        mContext = _c;
        mObjectVehicles = _objectVehicles;
    }

    @Override
    public int getCount() {

        if (mObjectVehicles == null) {
            return 0;
        }

        return mObjectVehicles.size();
    }

    @Override
    public ObjectVehicles getItem(int position) {

        if (mObjectVehicles == null || position < 0 || position >= mObjectVehicles.size()) {
            return null;
        }

        return mObjectVehicles.get(position);
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

        ObjectVehicles objectVehicles = getItem(position);

        if (objectVehicles != null) {

            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(objectVehicles.getMake());

            tv = (TextView) convertView.findViewById(android.R.id.text2);
            tv.setText(objectVehicles.getModel());
        }

        return convertView;
    }
}
