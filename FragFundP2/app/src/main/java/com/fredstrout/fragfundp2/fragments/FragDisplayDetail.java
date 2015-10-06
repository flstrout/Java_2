package com.fredstrout.fragfundp2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fredstrout.fragfundp2.R;

/**
 * Created by fredstrout on 10/6/15.
 */
public class FragDisplayDetail extends Fragment {

    public static final String TAG = "FragDisplayDetail.TAG";
    private static final String ARG_TEXT = "FragDisplayDetail.ARG_TEXT";

    public static FragDisplayDetail newInstance(String text) {

        FragDisplayDetail frag = new FragDisplayDetail();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_display_detail, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if (args !=null && args.containsKey(ARG_TEXT)){
            setDisplayText(args.getString(ARG_TEXT));
        }
    }

    public void setDisplayText (String text){

        getArguments().putString(ARG_TEXT, text);
        TextView tv = (TextView) getView().findViewById(R.id.vehicle_model);
        tv.setText(text);

    }
}
