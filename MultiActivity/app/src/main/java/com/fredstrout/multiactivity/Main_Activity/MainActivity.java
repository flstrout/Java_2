package com.fredstrout.multiactivity.Main_Activity;

//    Fred Strout
//    Java 2
//    MultiActivity
//    10/9/2015

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fredstrout.multiactivity.Data_Utility.Opportunity;
import com.fredstrout.multiactivity.Detail_Activity.DetailActivity;
import com.fredstrout.multiactivity.New_Activity.NewActivity;
import com.fredstrout.multiactivity.R;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements FragmentMain.OnRowClickedListener{

    public static final int REQUESTCODE1 = 1;
    public static final int REQUESTCODE2 = 2;
    public static final String DELETEOPPORTUNITYEXTRA = "com.fredstrout.multiactivity.Delete";
    public static Context mContext;
    private ArrayList<Opportunity> allOpportunities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        FragmentManager mgr = getFragmentManager();
        FragmentTransaction trans = mgr.beginTransaction();

        FragmentMain frag = FragmentMain.newInstance();
        trans.replace(R.id.list_container, frag, FragmentMain.TAG);

        trans.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_new:
                Toast.makeText(this, "New Clicked", Toast.LENGTH_SHORT).show();
                Intent newIntent = new Intent(this, NewActivity.class);
                newIntent.putExtra("newData", allOpportunities);
                startActivityForResult(newIntent, REQUESTCODE2);
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode == Activity.RESULT_OK && requestCode == REQUESTCODE1){

            allOpportunities.remove(data.getIntExtra(DELETEOPPORTUNITYEXTRA, 0));
            FragmentMain fm = (FragmentMain) getFragmentManager().findFragmentById(R.id.list_container);
            fm.updateListAdapter();
        }
        if ( resultCode == Activity.RESULT_OK && requestCode == REQUESTCODE2){

            String mName = data.getStringExtra("name");
            String mResult = data.getStringExtra("result");
            String mProblem = data.getStringExtra("problem");
            allOpportunities.add(new Opportunity(mName, mResult, mProblem));
            FragmentMain fm = (FragmentMain) getFragmentManager().findFragmentById(R.id.list_container);
            fm.updateListAdapter();
        }
    }

    @Override
    public void viewOpportunity(int position) {

        Intent detailIntent = new Intent (this, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.OPPORTUNITYEXTRA, allOpportunities.get(position));
        detailIntent.putExtra(DetailActivity.DELETEEXTRA,position);
        startActivityForResult(detailIntent, REQUESTCODE1);
    }

    @Override
    public void getOpportunity(ArrayList<Opportunity> opportunity) {
        allOpportunities = opportunity;
    }
}