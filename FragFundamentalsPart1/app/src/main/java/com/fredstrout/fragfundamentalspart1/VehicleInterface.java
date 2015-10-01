package com.fredstrout.fragfundamentalspart1;

// Connected
// Created by Fred L. Strout
// 9/23/2015
// Java 1 - Project 4 -

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.ArrayList;

public class VehicleInterface extends AsyncTask<Void, Void, ArrayList<Vehicle>> {

    private ProgressDialog pd;

    public interface VehicleDataReceiver {
        void DataReceived(ArrayList<Vehicle> _vehicles);
    }

    VehicleDataReceiver mReceiver;

    public VehicleInterface(VehicleDataReceiver _receiver) {
        mReceiver = _receiver;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();;
        pd = new ProgressDialog (MainActivity.mContext);
        pd.setTitle("File Transfer");
        pd.setMessage("Downloading Data");
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        pd.setProgress(0);
        pd.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Vehicle> doInBackground(Void... params) {
        String vData = VehicleUtil.getVehicleData();
        ArrayList<Vehicle> vehicles = VehicleUtil.parseVehicleData(vData);
        return vehicles;
    }

    @Override
    protected void onPostExecute(ArrayList<Vehicle> vehicles) {
        super.onPostExecute(vehicles);

        if (pd != null) {
            pd.dismiss();
        }
        mReceiver.DataReceived(vehicles);
    }
}