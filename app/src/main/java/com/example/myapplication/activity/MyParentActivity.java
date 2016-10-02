package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.module.MyCustomObject;

public class MyParentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_parent);
    }

    public void createObject(View v) {
        // Create the custom object
        MyCustomObject object = new MyCustomObject();
        // Step 4 - Setup the listener for this object
        object.setCustomObjectListener(new MyCustomObject.MyCustomObjectListener() {
            @Override
            public void onObjectReady(String title) {
                // Code to handle object ready
            }

            @Override
            public void onDataLoaded(int dataType) {
                // Code to handle data loaded from network
                // Use the data here!
                Toast.makeText(MyParentActivity.this, "Data is loaded " + dataType, Toast.LENGTH_LONG).show();
            }
        });
    }
}
