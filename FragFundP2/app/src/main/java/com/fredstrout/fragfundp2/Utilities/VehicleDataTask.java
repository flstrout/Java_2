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

//        if (netCheck()) {
////
//            ArrayAdapter<ObjectVehicles> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, objectVehicles);
//            setListAdapter(adapter);
//            writeToFile(getContext(), "vehicles", objectVehicles);
//
//        } else {
//
////                storedVehicles.add(new ObjectVehicles("Not", "Working!"));
//            storedVehicles = readFromFile("vehicles");
//            ArrayAdapter<ObjectVehicles> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, storedVehicles);
//            setListAdapter(adapter);
//        }
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
