package com.fredstrout.sqliteotherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity{

    private Opportunity mOpportunity;

    public static final String OPPORTUNITYEXTRA = "com.fredstrout.sqliteotherapp.Opportunity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        TextView name = (TextView) findViewById(R.id.customer_name);
        TextView resolution = (TextView) findViewById(R.id.promised_resolution);
        TextView opportunity = (TextView) findViewById(R.id.problem);
        Switch resolved = (Switch) findViewById(R.id.resolved);

        Intent detailIntent = getIntent();

        if( detailIntent != null){
            mOpportunity = (Opportunity) detailIntent.getSerializableExtra(OPPORTUNITYEXTRA);
        }

        name.setText(mOpportunity.getCustomer());
        resolution.setText(mOpportunity.getResolution());
        opportunity.setText(mOpportunity.getOpportunity());
        resolved.setChecked(mOpportunity.getResolved());

        Toast.makeText(this, mOpportunity.getCustomer(), Toast.LENGTH_SHORT).show();
    }
}
