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

public class UtilityModels {
    private static final String TAG = "UtilityModels";
    private static String selectedMake = "AstonMartin";

    public static void setSelectedMake(String selectedMake1){
        selectedMake = selectedMake1;
    }

    private static final String MAIN_URL = "https://api.edmunds.com/api/vehicle/v2/" + selectedMake + "/makes?fmt=json&api_key=23n4wrcrhkucaevmmp9x7vf8";

    public static String getModelData() {

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

    public static ArrayList<ObjectModels> parseModelData(String _modelData) {

        ArrayList<ObjectModels> models = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(_modelData);
            JSONArray modelsArr = obj.getJSONArray("models");

            for (int n = 0; n < modelsArr.length(); n++) {
                JSONObject vehicleObj = modelsArr.getJSONObject(n);
                JSONArray modelArr = vehicleObj.getJSONArray("models");
                String model = vehicleObj.getString("name");
                for (int m = 0; m < 1; m++){
                    JSONObject modelObj = modelArr.getJSONObject(m);
                    String id = modelObj.getString("id");
                    models.add(new ObjectModels(model, id));
                }

            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return models;
    }
}