package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class DayNightActi extends AppCompatActivity {

    private Button btnAutoMode, btnNightMode, btnDayMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_night);

        //Default Night Mode as Auto Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
        btnAutoMode = (Button) findViewById(R.id.btnAutoMode);
        btnNightMode = (Button) findViewById(R.id.btnNightMode);
        btnDayMode = (Button) findViewById(R.id.btnDayMode);


        btnAutoMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Auto Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                Intent i = new Intent(DayNightActi.this, ModeActivity.class);
                startActivity(i);
            }
        });

        btnNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Default Night Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent i = new Intent(DayNightActi.this, ModeActivity.class);
                startActivity(i);
            }
        });

        btnDayMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set Default Day Mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent i = new Intent(DayNightActi.this, ModeActivity.class);
                startActivity(i);
            }
        });
    }
}
