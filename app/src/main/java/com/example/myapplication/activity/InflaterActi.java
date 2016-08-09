package com.example.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.R;

public class InflaterActi extends AppCompatActivity {

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);

        layout = (RelativeLayout) findViewById(R.id.relative);
        // Since the third parameter is true, view1 should be the RelativeLayout
        View view1 = getLayoutInflater().inflate(R.layout.inflater_test1, layout, true);

        Toast.makeText(this, "The type is " + view1.getClass().getName(), Toast.LENGTH_SHORT).show();
    }

    public void addView(View v) {
        // Another way to get inflater
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Since the third parameter is false, view2 should be the LinearLayout
        View view2 = inflater.inflate(R.layout.inflater_test2, layout, false);
        layout.addView(view2, 2);

        Toast.makeText(this, "The type is " + view2.getClass().getName(), Toast.LENGTH_SHORT).show();
    }
}
