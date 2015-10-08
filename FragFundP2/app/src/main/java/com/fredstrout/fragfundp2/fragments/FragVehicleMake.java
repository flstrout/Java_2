package com.fredstrout.fragfundp2.fragments;

// FragFundP2
// Created by Fred L. Strout
// 10/06/2015
// Java 2 - Project 2

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fredstrout.fragfundp2.MainActivity;
import com.fredstrout.fragfundp2.Utilities.ObjectVehicles;
import com.fredstrout.fragfundp2.Utilities.UtilityVehicles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

    private class VehicleDataTask extends AsyncTask<Void, Void, ArrayList<ObjectVehicles>>{
        @Override
        protected ArrayList<ObjectVehicles> doInBackground(Void... params) {

            String vData = UtilityVehicles.getVehicleData();
            ArrayList<ObjectVehicles> objectVehicles = UtilityVehicles.parseVehicleData(vData);
            return objectVehicles;
        }

        @Override
        protected void onPostExecute(ArrayList<ObjectVehicles> objectVehicles) {
            super.onPostExecute(objectVehicles);

            ArrayList<ObjectVehicles> storedVehicles = new ArrayList<>();

            if (netCheck()) {
//
                ArrayAdapter<ObjectVehicles> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, objectVehicles);
                setListAdapter(adapter);
                writeToFile(getContext(), "vehicles", objectVehicles);

            } else {

//                storedVehicles.add(new ObjectVehicles("Not", "Working!"));
                storedVehicles = readFromFile("vehicles");
                ArrayAdapter<ObjectVehicles> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, storedVehicles);
                setListAdapter(adapter);
            }
        }
    }

    private void writeToFile(Context _c, String _filename, ArrayList<ObjectVehicles> _data) {
        File external = _c.getExternalFilesDir(null);
        if (external == null) {
            throw new IllegalStateException("Screaming monkeys - what is happening?!");
        }

        File file = new File(external, _filename);

        try {
            FileOutputStream fos = MainActivity.mContext.openFileOutput ( _filename, Context.MODE_PRIVATE );

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(_data);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("Test", external.getAbsolutePath());
    }

    private ArrayList readFromFile(String _filename) {
        File external = getContext().getExternalFilesDir(null);
        File file = new File(external, _filename);

        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fin);

            ArrayList<ObjectVehicles> vehicles = (ArrayList<ObjectVehicles>)oin.readObject();

            oin.close();
            return vehicles;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean netCheck() {

        ConnectivityManager mgr = (ConnectivityManager) MainActivity.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(mgr != null){
            NetworkInfo info = mgr.getActiveNetworkInfo();
            if (info != null && info.isAvailable()){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        VehicleDataTask task = new VehicleDataTask();
        task.execute();
    }

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {

        String vehicle = (String)_l.getItemAtPosition(_position).toString();
        mListener.displayText(vehicle);
    }
}
