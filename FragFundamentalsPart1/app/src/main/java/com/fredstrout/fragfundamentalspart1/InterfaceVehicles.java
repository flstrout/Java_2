package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class InterfaceVehicles extends AsyncTask<Void, Void, ArrayList<ObjectVehicles>>{

    public interface VehicleDataReceiver {
        void DataReceived(ArrayList<ObjectVehicles> _objectVehicles);
    }

    VehicleDataReceiver mReceiver;

    public InterfaceVehicles(VehicleDataReceiver _receiver) {
        mReceiver = _receiver;
    }

    @Override
    protected ArrayList<ObjectVehicles> doInBackground(Void... params) {
        if (netCheck()) {
            String vData = UtilityVehicles.getVehicleData();
            ArrayList<ObjectVehicles> objectVehicles = UtilityVehicles.parseVehicleData(vData);
            writeToFile("vehicles", objectVehicles);
            return objectVehicles;
        } else {

            ArrayList<ObjectVehicles> objectVehicles = readFromFile("vehicles");

//            ArrayList<ObjectVehicles> objectVehicles = new ArrayList<>();
//            objectVehicles.add(new ObjectVehicles("Fred", "Strout"));
            return objectVehicles;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<ObjectVehicles> objectVehicles) {
        super.onPostExecute(objectVehicles);

        if (!netCheck())
        {        new AlertDialog.Builder(MainActivity.mContext)
                .setTitle("ObjectVehicles")
                .setMessage("No Data Available")
                .setPositiveButton("OK", null)
                .show();
        }
        mReceiver.DataReceived(objectVehicles);
    }

    private void writeToFile(String _filename, ArrayList<ObjectVehicles> _data) {
        File external = MainActivity.mContext.getExternalFilesDir(null);
        File file = new File(external, _filename);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(_data);
            oos.close();
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<ObjectVehicles> readFromFile(String _filename) {
        ArrayList<ObjectVehicles> savedArrayList = new ArrayList<>();

        File external = MainActivity.mContext.getExternalFilesDir(null);
        File file = new File(external, _filename);

            try {
                FileInputStream fin = new FileInputStream(file);

                // Wrapping our stream
                ObjectInputStream ois = new ObjectInputStream(fin);

                // Reading in our object
                savedArrayList = (ArrayList<ObjectVehicles>)ois.readObject();
                // Closing our object stream which also closes the wrapped stream.
                ois.close();
                return savedArrayList;
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
}