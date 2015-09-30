package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class VehicleUtil {
    private static final String TAG = "VehicleUtil";
    private static final String MAIN_URL = "https://api.edmunds.com/api/vehicle/v2/makes?fmt=json&api_key=23n4wrcrhkucaevmmp9x7vf8";

    public static String getVehicleData() {

        try {
            URL url = new URL(MAIN_URL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            String theData = IOUtils.toString(is);
            is.close();
            connection.disconnect();

            return theData;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Vehicle> parseVehicleData (String _vehicleData) {

        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        try {
            JSONObject obj = new JSONObject(_vehicleData);
            JSONArray vehiclesArr = obj.getJSONArray("makes");

            for (int n = 0; n < vehiclesArr.length(); n++) {
                JSONObject vehicleObj = vehiclesArr.getJSONObject(n);
                JSONArray modelArr = vehicleObj.getJSONArray("models");
                String make = vehicleObj.getString("name");
                for (int p = 0; p < modelArr.length(); p++) {
                    JSONObject modelObj = modelArr.getJSONObject(p);
                    String model = modelObj.getString("name");
                    vehicles.add(new Vehicle(make, null));
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return vehicles;
    }
}
