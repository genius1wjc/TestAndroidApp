package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.Student;

public class ParcelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(ParcelActivity.this, ProfileActivity.class);
                i.putExtra("student", new Student("name", 2, false, Student.Grade.TWO));
                startActivity(i);

                // ProfileActivity can retrieve Student like this
                /*@Override
                protected void onCreate(Bundle savedInstanceState) {
                    Student student = (Student) getIntent().getParcelableExtra("student");
                }*/
            }
        });
    }

}
