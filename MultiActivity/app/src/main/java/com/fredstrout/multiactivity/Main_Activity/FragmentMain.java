package com.fredstrout.multiactivity.Main_Activity;

//    Fred Strout
//    Java 2
//    MultiActivity
//    10/9/2015

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.fredstrout.multiactivity.Data_Utility.AdapterOpportunity;
import com.fredstrout.multiactivity.Data_Utility.Opportunity;
import com.fredstrout.multiactivity.R;

import java.util.ArrayList;

public class FragmentMain extends ListFragment{


    AdapterOpportunity adapter;
    public static final String TAG = "FragmentMain.TAG";
    private OnRowClickedListener mListener;

    public static FragmentMain newInstance() {
        FragmentMain frag = new FragmentMain();
        return frag;
    }

    public interface OnRowClickedListener{
        public void viewOpportunity(int position);
        public void getOpportunity(ArrayList<Opportunity> opportunity);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnRowClickedListener){
            mListener = (OnRowClickedListener) activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the OnRowClickedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        ArrayList<Opportunity> opportunities = new ArrayList<Opportunity>();
        opportunities.add(new Opportunity("George Washington", "Free McDouble", "Missing Item"));
        opportunities.add(new Opportunity("John Adams", "Free Lg Fry", "Fries were cold"));
        opportunities.add(new Opportunity("Thomas Jefferson", "Free Big Mac", "Sandwich was missing the meat"));

        adapter = new AdapterOpportunity(MainActivity.mContext, opportunities);
        setListAdapter(adapter);
        mListener.getOpportunity(opportunities);
    }

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        Opportunity opportunity = (Opportunity)_l.getItemAtPosition(_position);
//        new AlertDialog.Builder(getActivity())
//                .setTitle(opportunity.getCustomer())
//                .setMessage(opportunity.getOpportunity() + "\n\n" + opportunity.getResolution())
//                .setPositiveButton("OK", null)
//                .show();
        mListener.viewOpportunity(_position);
    }

    public void updateListAdapter(){
        BaseAdapter adapterOpportunity = (BaseAdapter) adapter;
        adapterOpportunity.notifyDataSetChanged();
    }
}
