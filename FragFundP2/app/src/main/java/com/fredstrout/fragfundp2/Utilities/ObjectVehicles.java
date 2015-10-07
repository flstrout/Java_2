package com.fredstrout.fragfundp2.Utilities;

// FragFundP2
// Created by Fred L. Strout
// 10/06/2015
// Java 2 - Project 2

public class ObjectVehicles {

    private String mMake;
    private String mModel;
    private String mNoSpace;

    public ObjectVehicles() {
        mMake = mModel = mNoSpace = "";
    }

    public ObjectVehicles(String _make, String _model) {

        mMake = _make;
        mModel = _model;
        mNoSpace = _model.replaceAll("\\s", "");

    }

    public String getMake() { return mMake; }

    public String getModel() { return mModel; }

    @Override
    public String toString() { return mMake + ": " + mModel; }

}
