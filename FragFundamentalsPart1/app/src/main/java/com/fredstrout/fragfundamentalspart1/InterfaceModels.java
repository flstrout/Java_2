package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.ArrayList;

public class InterfaceModels extends AsyncTask<Void, Void, ArrayList<ObjectModels>> {

    private ProgressDialog pd;

    public interface ModelDataReceiver {
        void DataReceived(ArrayList<ObjectModels> _objectModels);
    }

    ModelDataReceiver oReceiver;

    public InterfaceModels(ModelDataReceiver _receiver) {
        oReceiver = _receiver;
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
    protected ArrayList<ObjectModels> doInBackground(Void... params) {
        String vData = UtilityModels.getModelData();
        ArrayList<ObjectModels> objectModels = UtilityModels.parseModelData(vData);
        return objectModels;
    }

    @Override
    protected void onPostExecute(ArrayList<ObjectModels> objectModels) {
        super.onPostExecute(objectModels);

        if (pd != null) {
            pd.dismiss();
        }
        oReceiver.DataReceived(objectModels);
    }
}