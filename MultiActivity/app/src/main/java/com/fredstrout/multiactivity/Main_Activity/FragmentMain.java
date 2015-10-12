package com.fredstrout.multiactivity.Main_Activity;

//    Fred Strout
//    Java 2
//    MultiActivity
//    10/9/2015

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fredstrout.multiactivity.Data_Utility.AdapterOpportunity;
import com.fredstrout.multiactivity.Data_Utility.Opportunity;

import java.util.ArrayList;

public class FragmentMain extends ListFragment{

    public static final String TAG = "FragmentMain.TAG";
    public static FragmentMain newInstance() {
        FragmentMain frag = new FragmentMain();
        return frag;
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        ArrayList<Opportunity> opportunities = new ArrayList<Opportunity>();
        opportunities.add(new Opportunity("George Washington", "Free McDouble", "Missing Item"));
        opportunities.add(new Opportunity("John Adams", "Free Lg Fry", "Fries were cold"));
        opportunities.add(new Opportunity("Thomas Jefferson", "Free Big Mac", "Sandwich was missing the meat"));

        AdapterOpportunity adapter = new AdapterOpportunity(MainActivity.mContext, opportunities);
        setListAdapter(adapter);
    }
    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        Opportunity opportunity = (Opportunity)_l.getItemAtPosition(_position);
        new AlertDialog.Builder(getActivity())
                .setTitle(opportunity.getCustomer())
                .setMessage(opportunity.getOpportunity() + "\n\n" + opportunity.getResolution())
                .setPositiveButton("OK", null)
                .show();
    }

}
