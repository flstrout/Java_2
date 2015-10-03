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
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class InterfaceVehicles extends AsyncTask<Void, Void, ArrayList<ObjectVehicles>>{

    private ProgressDialog pd;

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
            writeToFile(MainActivity.mContext, "vehicles", objectVehicles.toString());
            return objectVehicles;
        } else {

//            readFromFile("vehicles");

            ArrayList<ObjectVehicles> objectVehicles = new ArrayList<>();
            objectVehicles.add (new ObjectVehicles("Fred", "Strout"));
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

    private void writeToFile(Context _c, String _filename, String _data) {
        File external = _c.getExternalFilesDir(null);
        File file = new File(external, _filename);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Write bytes to the stream
            oos.write(_data.getBytes());
            // Close the stream to save the file.
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(String _filename) {
        File external = MainActivity.mContext.getExternalFilesDir(null);
        File file = new File(external, _filename);

        try {
            FileInputStream fin = new FileInputStream(file);
            InputStreamReader inReader = new InputStreamReader(fin);
            BufferedReader reader = new BufferedReader(inReader);

            // Reading data from our file using the reader
            // and storing it our string buffer.
            StringBuffer buffer = new StringBuffer();
            String text = null;
            // Make sure a line of text is available to be read.
            while((text = reader.readLine()) != null) {
                buffer.append(text + "\n");
            }
            // Close the reader and underlying stream.
            reader.close();
            // Convert the buffer to a string.
            return buffer.toString();
        } catch(IOException e) {
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