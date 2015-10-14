package com.fredstrout.multiactivity.Data_Utility;

//    Fred Strout
//    Java 2
//    MultiActivity
//    10/9/2015

import java.io.Serializable;

public class Opportunity implements Serializable {

    private String mCustomer;
    private String mResolution;
    private String mOpportunity;

    public Opportunity() {
        mCustomer = mResolution = mOpportunity = "";
    }

    public Opportunity(String _customer, String _resolution, String _opportunity) {

        mCustomer = _customer;
        mResolution = _resolution;
        mOpportunity = _opportunity;
    }

    public String getCustomer() { return mCustomer; }
    public String getResolution() { return mResolution; }
    public String getOpportunity() { return mOpportunity; }

    public void setCustomer(String mCustomer) { this.mCustomer = mCustomer; }
    public void setResolution(String mResolution) { this.mResolution = mResolution; }
    public void setOpportunity(String mOpportunity) { this.mOpportunity = mOpportunity; }

    @Override
    public String toString() { return mCustomer; }
}
