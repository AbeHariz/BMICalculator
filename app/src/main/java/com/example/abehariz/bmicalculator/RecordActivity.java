package com.example.abehariz.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity  {

    TextView tvStored;
    private String file = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_record );

        tvStored = (TextView) findViewById( R.id.displaysaveditem );

        //setContentView( R.layout.activity_record );


        //Read from file
        final DBHandler db = new DBHandler( this );

        Log.d( "Reading:", "Reading all records.." );

        List<Record> records = db.getAllRecords();
        String log = "";
        for (Record record : records) {
            log = log + "ID: " + record.getId() + " ,BMI: " + record.getBmi() + " ,DATE: " + record.getDate() + "\n";
            // Writing records to log
            Log.d( "Reading record: ", log );
        }
        tvStored.setText( log );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_options, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_home:
                startActivity( new Intent( RecordActivity.this, CalculatorActivity.class ) );
                finish();
                break;
            case R.id.menu_info:
                startActivity( new Intent( RecordActivity.this, InformationActivity.class ) );
                finish();
                break;
            default:
                return super.onOptionsItemSelected( item );
        }
        return true;
    }
}
