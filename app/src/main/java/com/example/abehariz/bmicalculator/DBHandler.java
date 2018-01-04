package com.example.abehariz.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static java.util.Calendar.DATE;

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    public static final int DATABASE_VERSION = 20;
    // Database Name
    public static final String DATABASE_NAME = "RecordDB";
    // Record table name
    public static final String TABLE_RECORD = "recordtable";
    // Record Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_BMI = "bmi";
    public static final String KEY_DATE = "date";

    public DBHandler(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECORD_TABLE = "CREATE TABLE " + TABLE_RECORD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BMI + " TEXT, " + KEY_DATE + " TEXT)";
        db.execSQL( CREATE_RECORD_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_RECORD );
        // Creating tables again
        onCreate( db );
    }

    public void addBmi(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( KEY_BMI, record.getBmi() ); // BMI value
        values.put( KEY_DATE, record.getDate() ); // Date
        // Inserting Row
        db.insert( TABLE_RECORD, null, values );
        db.close(); // Closing database connection
    }

    public List<Record> getAllRecords() {
        List<Record> recordList = new ArrayList<Record>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_RECORD;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( selectQuery, null );
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setId( Integer.parseInt( cursor.getString( 0 ) ) );
                record.setBmi( cursor.getString( 1 ) );
                record.setDate( cursor.getString( 2 ) );
                // Adding record to list
                recordList.add( record );
            } while (cursor.moveToNext());
        }
        // return record list
        return recordList;
    }

    /*public List<Record> getSelectedRecords(String s) {
        String month = "00";
        if (s.equals( "January" )) {
            month = "01";
        } else if (s.equals( "February" )) {
            month = "02";
        } else if (s.equals( "March" )) {
            month = "03";
        } else if (s.equals( "April" )) {
            month = "04";
        } else if (s.equals( "May" )) {
            month = "05";
        } else if (s.equals( "June" )) {
            month = "06";
        } else if (s.equals( "July" )) {
            month = "07";
        } else if (s.equals( "August" )) {
            month = "08";
        } else if (s.equals( "September" )) {
            month = "09";
        } else if (s.equals( "October" )) {
            month = "10";
        } else if (s.equals( "November" )) {
            month = "11";
        } else if (s.equals( "December" )) {
            month = "12";
        }
        System.out.println( "GET " + s );
        List<Record> recordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_RECORD + "WHERE " + KEY_DATE + "LIKE '__/" + DATE + "/____';";
        System.out.printf( selectQuery );
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery( selectQuery, null );
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setId( Integer.parseInt( cursor.getString( 0 ) ) );
                record.setBmi( cursor.getString( 1 ) );
                record.setDate( cursor.getString( 2 ) );
                // Adding record to list
                recordList.add( record );
            } while (cursor.moveToNext());
        }
        // return record list
        return recordList;
    }


    public void deleteAllBmi() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_RECORD;
        db.execSQL(deleteQuery);
        db.close(); // Closing database connection
    }*/
}
