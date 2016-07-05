package com.example.myapplication.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.myapplication.R;
import com.example.myapplication.task.TestTask;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Switch switch1 = (Switch) findViewById(R.id.switch1);
        if (switch1 != null) {
            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        for (int i = 0; i < 4; i++) {
                            new TestTask(TasksActivity.this, i).execute();
                        }
                    }
                }
            });
        }

        Switch switch2 = (Switch) findViewById(R.id.switch2);
        if (switch2 != null) {
            switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        for (int i = 0; i < 4; i++) {
                            new TestTask(TasksActivity.this, i).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }
                    }
                }
            });
        }
    }
}
