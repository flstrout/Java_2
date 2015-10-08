package com.fredstrout.fragfundp2.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.fredstrout.fragfundp2.MainActivity;

import java.util.ArrayList;

/**
 * Created by fredstrout on 10/7/15.
 */
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
