package com.fredstrout.fragfundp2.Utilities;

// FragFundP2
// Created by Fred L. Strout
// 10/06/2015
// Java 2 - Project 2

import android.os.AsyncTask;
import java.util.ArrayList;

public class VehicleDataTask extends AsyncTask<Void, Void, ArrayList<ObjectVehicles>> {

    public interface VehicleDataReceiver {
        void DataReceived (ArrayList<ObjectVehicles> _vehicles);
    }

    VehicleDataReceiver mReceiver;

    public VehicleDataTask(VehicleDataReceiver _receiver) {
        mReceiver = _receiver;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<ObjectVehicles> doInBackground(Void... params) {

        String vData = UtilityVehicles.getVehicleData();
        ArrayList<ObjectVehicles> objectVehicles = UtilityVehicles.parseVehicleData(vData);
        return objectVehicles;
    }

    @Override
    protected void onPostExecute(ArrayList<ObjectVehicles> objectVehicles) {
        super.onPostExecute(objectVehicles);

        mReceiver.DataReceived(objectVehicles);
    }
}
