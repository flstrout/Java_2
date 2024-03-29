package com.fredstrout.sqliteapp;

//    Java II
//    Fred Strout
//    Content Provider - Database App

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

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

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_OPPORTUNITIES + " (" +
                    OPPORTUNITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    OPPORTUNITY_NAME + " TEXT, " +
                    OPPORTUNITY_RESOLUTION + " TEXT, " +
                    OPPORTUNITY_PROBLEM + " TEXT, " +
                    OPPORTUNITY_CREATED + " INTEGER" +
                    ")";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF EXISTS " + TABLE_OPPORTUNITIES);
        onCreate(db);

    }
}