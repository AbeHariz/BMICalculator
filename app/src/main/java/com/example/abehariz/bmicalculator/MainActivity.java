package com.example.abehariz.bmicalculator;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button deleteall;

    private long delay = 1000;
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            startActivity( new Intent( MainActivity.this, CalculatorActivity.class ) );
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timer timer = new Timer();
        timer.schedule( task, delay );

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        deleteall = (Button) findViewById( R.id.deleteAll );

        final DBHandler db = new DBHandler( this );
        Log.d( "New DBHandler", "SUCCESSFULL" );

        db.addBmi( new Record( 1, "25", "04/01/2018" ) );
        Log.d( "Insert Data", "SUCCESSFULL" );

        Log.d( "Reading: ", "Reading all records.." );
        List<Record> records = db.getAllRecords();
        String log = "";
        for (Record record : records) {
            log = "ID:  " + record.getId() + " ,BMI:  " + record.getBmi() + " ,DATE: " + record.getDate();
            //Writing records to log
            Log.d( "Reading records: ", log );
        }

        /*deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteAllBmi();
                    Toast.makeText(getBaseContext(),"All data deleted",Toast.LENGTH_SHORT).show();
                    Log.d("Deleting record: ", "SUCCESS DELETING ALL RECORD");
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    finish();
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }); */
    }
}