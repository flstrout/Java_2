package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mContext = this;



//        FragmentManager mgr = getFragmentManager();
//        FragmentTransaction trans = mgr.beginTransaction();
//
//        FragmentMasterList frag = FragmentMasterList.newInstance();
//        trans.replace(R.id.fragment_container, frag, FragmentMasterList.TAG);
//
//        trans.commit();

//        FragmentManager mgr1 = getFragmentManager();
//        FragmentTransaction trans1 = mgr1.beginTransaction();
//
//        FragmentModelList frag1 = FragmentModelList.newInstance();
//        trans1.replace(R.id.fragment_container1, frag, FragmentModelList.TAG);
//
//        trans1.commit();
    }


}