package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

public class ObjectModels {

    private String mModel;
    private String mID;

    public ObjectModels() {
        mModel = mID = "";
    }

    public ObjectModels(String _model, String _id) { mModel = _model; mID = _id; }

    public String getModel() { return mModel; }

    public String getID() { return mID; }

    @Override
    public String toString() { return mModel; }

}
