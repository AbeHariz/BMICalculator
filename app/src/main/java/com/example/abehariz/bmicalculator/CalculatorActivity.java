package com.example.abehariz.bmicalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CalculatorActivity extends AppCompatActivity {
    Button bSave, bRead;
    TextView tvStored;
    String data;
    private String file = "mydata";

    private EditText etWeight, etHeight;
    private TextView tvDisplayResult, tvResultStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_calculator );

        etWeight = (EditText) findViewById( R.id.weight );
        etHeight = (EditText) findViewById( R.id.height );
        tvDisplayResult = (TextView) findViewById( R.id.display_result );
        tvResultStatus = (TextView) findViewById( R.id.result_status );

        bSave = (Button) findViewById( R.id.savebutton );
        bRead = (Button) findViewById( R.id.readbutton );
        tvStored = (TextView) findViewById( R.id.displaysaveditem );
        final TextView datestored = (TextView) findViewById( R.id.textview1 );

        bSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = tvDisplayResult.getText().toString() + "\n";
                String date = datestored.getText().toString();
                try {
                    final DBHandler db = new DBHandler( CalculatorActivity.this );
                    db.addBmi( new Record( 1, data, date ) );
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } );

        //Read
        bRead.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( CalculatorActivity.this, RecordActivity.class ) );
                finish();
            }
        } );
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_calculate:
                calculate();
                break;
            case R.id.button_reset:
                reset();
                break;
        }
    }

    private void calculate() {
        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();

        double bmiResult = Double.parseDouble( weight ) / (Double.parseDouble( height ) * Double.parseDouble( height ));

        if (bmiResult < 18.5) {
            tvResultStatus.setText( R.string.under );
            tvResultStatus.setBackgroundColor( Color.parseColor( "#F1C40F" ) );
            tvResultStatus.setTextColor( Color.parseColor( "#FFFFFF" ) );
        } else if (bmiResult > 18.5 && bmiResult < 24.9) {
            tvResultStatus.setText( R.string.normal );
            tvResultStatus.setBackgroundColor( Color.parseColor( "#2ECC71" ) );
            tvResultStatus.setTextColor( Color.parseColor( "#FFFFFF" ) );
        } else if (bmiResult > 24.9 && bmiResult < 29.9) {
            tvResultStatus.setText( R.string.over );
            tvResultStatus.setBackgroundColor( Color.parseColor( "#E57E22" ) );
            tvResultStatus.setTextColor( Color.parseColor( "#FFFFFF" ) );
        } else if (bmiResult > 29.9) {
            tvResultStatus.setText( R.string.obese );
            tvResultStatus.setBackgroundColor( Color.parseColor( "#C0392B" ) );
            tvResultStatus.setTextColor( Color.parseColor( "#FFFFFF" ) );
        }

        tvDisplayResult.setText( String.valueOf( bmiResult ) );
    }

    private void reset() {
        etWeight.setText( "" );
        etHeight.setText( "" );
        tvDisplayResult.setText( R.string.default_result );
        tvResultStatus.setText( R.string.na );
        tvResultStatus.setBackgroundColor( Color.parseColor( "#edeeef" ) );
        tvResultStatus.setTextColor( Color.parseColor( "#FFFFFF" ) );
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_options, menu );
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_home:
                startActivity( new Intent( CalculatorActivity.this, CalculatorActivity.class ) );
                finish();
                break;
            case R.id.menu_info:
                startActivity( new Intent( CalculatorActivity.this, InformationActivity.class ) );
                finish();
                break;
            default:
                return super.onOptionsItemSelected( item );
        }
        return true;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show( getSupportFragmentManager(), "datePicker" );
    }

}
