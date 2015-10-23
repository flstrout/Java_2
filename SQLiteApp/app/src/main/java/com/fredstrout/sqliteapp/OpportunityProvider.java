package com.fredstrout.sqliteapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class OpportunityProvider extends ContentProvider{

    private static final String AUTHORITY = "com.fredstrout.sqliteapp.opportunityprovider";
    private static final String BASE_PATH = "opportunities";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    // Constant to identify the requested operation
    private static final int OPPORTUNITIES = 1;
    private static final int OPPORTUNITIES_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, OPPORTUNITIES);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", OPPORTUNITIES_ID);
    }
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query(DBOpenHelper.TABLE_OPPORTUNITIES, DBOpenHelper.ALL_COLUMNS, selection,
                null, null, null, DBOpenHelper.OPPORTUNITY_NAME + " ASC");
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert(DBOpenHelper.TABLE_OPPORTUNITIES, null, values);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete(DBOpenHelper.TABLE_OPPORTUNITIES, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update(DBOpenHelper.TABLE_OPPORTUNITIES, values, selection, selectionArgs);
    }
}