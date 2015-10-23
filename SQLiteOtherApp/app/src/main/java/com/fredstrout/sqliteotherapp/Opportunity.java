package com.fredstrout.sqliteotherapp;

//    Fred Strout
//    Java 2
//    MultiActivity
//    10/9/2015

import java.io.Serializable;

public class Opportunity implements Serializable {

    private String mCustomer;
    private String mResolution;
    private String mOpportunity;
    private int mResolved;

    public Opportunity() {
        mCustomer = mResolution = mOpportunity = "";
        mResolved = 0;
    }

    public Opportunity(String _customer, String _resolution, String _opportunity, int _resolved) {

        mCustomer = _customer;
        mResolution = _resolution;
        mOpportunity = _opportunity;
        mResolved = _resolved;
    }

    public String getCustomer() { return mCustomer; }
    public String getResolution() { return mResolution; }
    public String getOpportunity() { return mOpportunity; }
    public int getResolved() { return mResolved; }

    public void setCustomer(String mCustomer) { this.mCustomer = mCustomer; }
    public void setResolution(String mResolution) { this.mResolution = mResolution; }
    public void setOpportunity(String mOpportunity) { this.mOpportunity = mOpportunity; }
    public void setResolved(int mResolved) { this.mResolved = mResolved; }

    @Override
    public String toString() { return mCustomer; }
}
