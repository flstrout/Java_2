package com.fredstrout.fragfundamentalspart1;

// FragFundamentalsPart1
// Created by Fred L. Strout
// 9/30/2015
// Java 2 - Project 1

public class Vehicle {

    private String mMake;
    private String mModel;

    public Vehicle() {
        mMake = mModel = "";
    }

    public Vehicle(String _make, String _model) {

        mMake = _make;
        mModel = _model;

    }

    public String getMake() { return mMake; }

    public String getModel() { return mModel; }

    @Override
    public String toString() { return mModel; }

}
