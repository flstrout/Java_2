package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;

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

//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();;
//        pd = new ProgressDialog (MainActivity.mContext);
//        pd.setTitle("File Transfer");
//        pd.setMessage("Downloading Data");
//        pd.setCancelable(false);
//        pd.setIndeterminate(true);
//        pd.setProgress(0);
//        pd.show();
//    }

//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }

    @Override
    protected ArrayList<ObjectVehicles> doInBackground(Void... params) {
        String vData = UtilityVehicles.getVehicleData();
        ArrayList<ObjectVehicles> objectVehicles = UtilityVehicles.parseVehicleData(vData);
        return objectVehicles;
    }

    @Override
    protected void onPostExecute(ArrayList<ObjectVehicles> objectVehicles) {
        super.onPostExecute(objectVehicles);

//        if (pd != null) {
//            pd.dismiss();
//        }
        mReceiver.DataReceived(objectVehicles);
    }
}