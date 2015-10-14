package com.fredstrout.multiactivity.Detail_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fredstrout.multiactivity.Data_Utility.Opportunity;
import com.fredstrout.multiactivity.Main_Activity.MainActivity;
import com.fredstrout.multiactivity.New_Activity.NewActivity;
import com.fredstrout.multiactivity.R;

public class DetailActivity extends Activity {

    private Opportunity mOpportunity;
    private int mDelete;

    public static final String OPPORTUNITYEXTRA = "com.fredstrout.multiactivity.Opportunity";
    public static final String DELETEEXTRA = "com.fredstrout.multiactivity.Delete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        TextView name = (TextView) findViewById(R.id.the_customer);
        TextView resolution = (TextView) findViewById(R.id.the_promise);
        TextView opportunity = (TextView) findViewById(R.id.the_opportunity);

        Intent detailIntent = getIntent();

        if( detailIntent != null){
            mOpportunity = (Opportunity) detailIntent.getSerializableExtra(OPPORTUNITYEXTRA);
            mDelete = detailIntent.getIntExtra(DELETEEXTRA, 0);
        }

        name.setText(mOpportunity.getCustomer());
        resolution.setText(mOpportunity.getResolution());
        opportunity.setText(mOpportunity.getOpportunity());

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

        switch (id) {
            case android.R.id.home:
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void mirOnClick(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.DELETEOPPORTUNITYEXTRA, mDelete);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
