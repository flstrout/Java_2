package com.fredstrout.multiactivity.New_Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fredstrout.multiactivity.Data_Utility.Opportunity;
import com.fredstrout.multiactivity.Main_Activity.MainActivity;
import com.fredstrout.multiactivity.R;

import java.util.ArrayList;

public class NewActivity extends Activity {

    Context nContext;
//    private ArrayList<Opportunity> newData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        Button btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(submitClicked);
        nContext = this;

//        Intent newIntent = getIntent();
//        newData = (ArrayList<Opportunity>) newIntent.getSerializableExtra("newData");

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

    private View.OnClickListener submitClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ArrayList<Opportunity> newData = new ArrayList<Opportunity>();

            EditText custName = (EditText)findViewById(R.id.name_customer);
            EditText promResult = (EditText)findViewById(R.id.resolution_promised);
            EditText problem = (EditText)findViewById(R.id.problem);

            String name = custName.getText().toString();
            String result = promResult.getText().toString();
            String prob = problem.getText().toString();

            if ("".equals(custName.getText().toString()) || "".equals(promResult.getText().toString())) {
                Toast.makeText(nContext, "Required fields missing data! Please input the appropriate data for these fields or exit without saving.", Toast.LENGTH_LONG).show();
            } else {
                if ("".equals(problem.getText().toString())) {
                    prob = "None Specified";
                }
                newData.add(new Opportunity(name, result, prob));
                Toast.makeText(nContext, (name + "'s Opportunity was saved succesfully!"), Toast.LENGTH_SHORT).show();

                Intent returnNewIntent = new Intent();
                returnNewIntent.putExtra("name", name);
                returnNewIntent.putExtra("result", result);
                returnNewIntent.putExtra("problem", prob);
                setResult(RESULT_OK, returnNewIntent);
                finish();
            }
        }
    };
}
