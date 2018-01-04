package com.example.abehariz.bmicalculator;

import android.support.v7.app.AppCompatActivity;

public class Record extends AppCompatActivity {
    private int id;
    private String bmi;
    private String date;

    public Record(int id, String bmi, String date) {
        this.id = id;
        this.bmi = bmi;
        this.date = date;
    }

    public Record() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }
}
