package com.fredstrout.sqliteotherapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int REQUESTCODE1 = 1;
    private CursorAdapter adapter;
    public static Context mContext;
    private ArrayList<Opportunity> selectedOpportunity = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        String[] from = {Constants.OPPORTUNITY_NAME};
        int[] to = {android.R.id.text1};
        adapter = new SimpleCursorAdapter(mContext, android.R.layout.simple_list_item_1, null, from, to, 0);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemClickListener(listClickListener);
        lv.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
    }

    // Do this when an item is clicked in the ListView (only available in landscape)
    AdapterView.OnItemClickListener listClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            Uri uri = Uri.parse(Constants.CONTENT_URI + "/" + l);
            Cursor cursor = getContentResolver().query(Constants.CONTENT_URI, Constants.ALL_COLUMNS, Constants.OPPORTUNITY_ID + "=" + l, null, null);
            cursor.moveToFirst();
            String selName = cursor.getString(cursor.getColumnIndex(Constants.OPPORTUNITY_NAME));

            String selResolution = cursor.getString(cursor.getColumnIndex(Constants.OPPORTUNITY_RESOLUTION));
            if (selResolution == null){selResolution = "";}

            String selProblem = cursor.getString(cursor.getColumnIndex(Constants.OPPORTUNITY_PROBLEM));
            if (selProblem == null){selProblem = "";}

            int selResolved = cursor.getInt(cursor.getColumnIndex(Constants.OPPORTUNITY_CREATED));

            selectedOpportunity.clear();
            selectedOpportunity.add(new Opportunity(selName, selResolution, selProblem, selResolved));


            luanchIntent();

        }
    }; // End OnItemClickListener

    private void luanchIntent() {
        Intent detailIntent = new Intent(mContext, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.OPPORTUNITYEXTRA, selectedOpportunity.get(0));
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                Toast.makeText(mContext, "Settings Clicked!", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext, Constants.CONTENT_URI, null, null, null, null);
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
