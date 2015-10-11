package com.fredstrout.multiactivity.Data_Utility;

//    Fred Strout
//    Java 2
//    MultiActivity
//    10/9/2015

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterOpportunity extends BaseAdapter {

    public final static int ID_CONSTANT = 0x00010001;

    Context mContext;
    ArrayList<Opportunity> mOpportunities;

    public AdapterOpportunity(Context _c, ArrayList<Opportunity> _opportunities){
        mContext = _c;
        mOpportunities = _opportunities;
    }

    @Override
    public int getCount() {

        if (mOpportunities == null) {
            return 0;
        }

        return mOpportunities.size();
    }

    @Override
    public Opportunity getItem(int position) {

        if (mOpportunities == null || position < 0 || position >= mOpportunities.size()) {
            return null;
        }

        return mOpportunities.get(position);
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

        Opportunity thisOpportunity = getItem(position);

        if (thisOpportunity != null) {

            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(thisOpportunity.getCustomer());

            tv = (TextView) convertView.findViewById(android.R.id.text2);
            tv.setText("   " + thisOpportunity.getResolution());
        }

        return convertView;
    }
}
