package com.fredstrout.sqliteapp;

//    Java II
//    Fred Strout
//    Content Provider - Database App

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>{

    private CursorAdapter adapter;
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        String[] from = {DBOpenHelper.OPPORTUNITY_NAME};
        int[] to = {android.R.id.text1};
        adapter = new SimpleCursorAdapter(mContext, android.R.layout.simple_list_item_1, null, from, to, 0);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

    }

    private void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }

    private void insertOpportunity(String name, String promise, String problem, Integer resolved) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.OPPORTUNITY_NAME, name);
        values.put(DBOpenHelper.OPPORTUNITY_RESOLUTION, promise);
        values.put(DBOpenHelper.OPPORTUNITY_PROBLEM, problem);
        values.put(DBOpenHelper.OPPORTUNITY_CREATED, resolved);
        Uri opportunityUri = getContentResolver().insert(OpportunityProvider.CONTENT_URI, values);
        Log.i("MainActivity", "Saved Opportunity " + opportunityUri.getLastPathSegment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_create:
                createData();
                break;
            case R.id.action_delete:
                deleteData();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteData() {
        DialogInterface.OnClickListener dialogClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int button) {
                        if (button == DialogInterface.BUTTON_POSITIVE) {
                            getContentResolver().delete(OpportunityProvider.CONTENT_URI, null, null);
                            restartLoader();
                            Toast.makeText(MainActivity.this,
                                    getString(R.string.all_deleted),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                .setNegativeButton(getString(android.R.string.no), dialogClickListener)
                .show();
    }

    private void createData() {
        insertOpportunity("Bruce Wayne", "Free Big Mac", "Dressed Wrong", 0);
        insertOpportunity("Diana Prince", "Free Salad", "Lettuce Old", 1);
        insertOpportunity("Clark Kent", "Free Parfait", "Not in Bag", 1);
        insertOpportunity("Lois Lane", "Free Snack Wrap", "Chicken Undercooked", 0);

        restartLoader();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext, OpportunityProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);

    }
}