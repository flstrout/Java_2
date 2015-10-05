package com.fredstrout.fragfundp2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by fredstrout on 10/5/15.
 */

public class FragVehicleMake extends ListFragment {

    public static final String TAG = "FragVehicleMake.TAG";

    private OnRowSelectedClickListener mListener;

    public static FragVehicleMake newInstance() {
        FragVehicleMake frag = new FragVehicleMake();
        return frag;
    }

    public interface OnRowSelectedClickListener{
        public void displayText(String text);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnRowSelectedClickListener){

            mListener = (OnRowSelectedClickListener) activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the OnRowSelectedClickListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        ArrayList<String> presidents = new ArrayList<String>();
        presidents.add("George Washington");
        presidents.add("John Adams");
        presidents.add("Thomas Jefferson");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, presidents);
        setListAdapter(adapter);
    }
    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {
        String president = (String)_l.getItemAtPosition(_position);
        mListener.displayText(president);
        new AlertDialog.Builder(getActivity())
                .setTitle(president)
                .setMessage(president)
                .setPositiveButton("OK", null)
                .show();
    }

}
