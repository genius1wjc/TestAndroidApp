package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.myapplication.GenericStudent;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GsonActivity extends AppCompatActivity {

    private static final String TAG = "GsonActivity";

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
    }

    public void gsonPrimitive(View v) {
        if (v instanceof CheckBox) {
            CheckBox box = (CheckBox)v;

            Boolean aBool = gson.fromJson("false", Boolean.class);
            String[] arr = {"abc", "123"};
            String result = gson.toJson(arr);
            Double one = gson.fromJson("1", Double.class);

            //Student s = new Student("Jiechao", 1, true, Student.Grade.THREE);

            //Student.Nested n = new Student.Nested();

            //Log.e(TAG, box.isChecked() + "");
            //Log.e(TAG, gson.toJson(s));
            //Log.e(TAG, gson.toJson(n));

            //String ss = gson.toJson(s);
            //Student student = gson.fromJson(ss, Student.class);
            //Log.e(TAG, student.toString());

            GenericStudent<Double> genericStudent = new GenericStudent<>(-3.4);

            Type studentType = new TypeToken<GenericStudent<Double>>() {}.getType();
            String json = gson.toJson(genericStudent, studentType);

            GenericStudent<Double> student1 = gson.fromJson(json, studentType);

            Log.e(TAG, student1.toString());
        }
    }
}
