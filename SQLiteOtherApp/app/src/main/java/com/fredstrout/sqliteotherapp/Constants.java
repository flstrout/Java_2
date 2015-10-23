package com.fredstrout.sqliteotherapp;

//    Java II
//    Fred Strout
//    Content Provider - No DB App

import android.net.Uri;

public class Constants {

    private static final String AUTHORITY = "com.fredstrout.sqliteapp.opportunityprovider";
    private static final String BASE_PATH = "opportunities";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    //Constants for db name and version
    private static final String DATABASE_NAME = "opportunities.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_OPPORTUNITIES = "opportunities";
    public static final String OPPORTUNITY_ID = "_id";
    public static final String OPPORTUNITY_NAME = "opportunityName";
    public static final String OPPORTUNITY_RESOLUTION = "opportunityResolution";
    public static final String OPPORTUNITY_PROBLEM = "opportunityProblem";
    public static final String OPPORTUNITY_CREATED = "opportunityCreated";

    public static final String [] ALL_COLUMNS = {OPPORTUNITY_ID, OPPORTUNITY_NAME, OPPORTUNITY_RESOLUTION, OPPORTUNITY_PROBLEM, OPPORTUNITY_CREATED};
}
