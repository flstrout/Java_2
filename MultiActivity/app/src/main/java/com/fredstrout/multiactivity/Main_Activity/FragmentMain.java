package com.fredstrout.multiactivity.Main_Activity;

//    Fred Strout
//    Java 2
//    MultiActivity
//    10/9/2015

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.fredstrout.multiactivity.Data_Utility.AdapterOpportunity;
import com.fredstrout.multiactivity.Data_Utility.Opportunity;
import com.fredstrout.multiactivity.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FragmentMain extends ListFragment{

    public ArrayList<Opportunity> opportunities = new ArrayList<Opportunity>();
    AdapterOpportunity adapter;
    public static final String TAG = "FragmentMain.TAG";
    private OnRowClickedListener mListener;

    public static FragmentMain newInstance() {
        FragmentMain frag = new FragmentMain();
        return frag;
    }

//    Interface
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

        opportunities = readFromFile(MainActivity.FILENAME);

        adapter = new AdapterOpportunity(MainActivity.mContext, opportunities);
        setListAdapter(adapter);
        mListener.getOpportunity(opportunities);

//        Long Click for Implicit Intent -> Text Message
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Opportunity opportunity = (Opportunity)arg0.getItemAtPosition(arg2);
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", "New Opportunity\n\nCustomer Name: " + opportunity.getCustomer()  + "\nOpportunity: " + opportunity.getOpportunity() + "\nPromised Resolution: \n" + opportunity.getResolution());
                startActivity(sendIntent);
                Toast.makeText(getActivity(), "On long click listener", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        Opportunity opportunity = (Opportunity)_l.getItemAtPosition(_position);
        mListener.viewOpportunity(_position);
    }

//    Refresh Display
    public void updateListAdapter(){
        BaseAdapter adapterOpportunity = (BaseAdapter) adapter;
        adapterOpportunity.notifyDataSetChanged();
    }

//    Get the data
    private ArrayList<Opportunity> readFromFile(String _filename) {
        File external = MainActivity.mContext.getExternalFilesDir(null);
//        Log.i("File", external.getAbsolutePath());
        File file = new File(external, _filename);

        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fin);

            ArrayList<Opportunity> allOpportunities = (ArrayList<Opportunity>)oin.readObject();

            oin.close();
            Log.i("File", allOpportunities.toString());
            return allOpportunities;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}