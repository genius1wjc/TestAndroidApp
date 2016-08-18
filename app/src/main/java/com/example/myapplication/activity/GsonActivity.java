package com.example.myapplication.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.myapplication.module.GenericStudent;
import com.example.myapplication.R;
import com.example.myapplication.module.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GsonActivity extends AppCompatActivity {

    private static final String TAG = "GsonActivity";

    private Gson gson = new Gson();

    private static final int PICK_CONTACT_REQUEST = 1;

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

            Student s = new Student("Jiechao", 1, true, Student.Grade.THREE);

            Student.Nested n = new Student.Nested();

            Log.d(TAG, box.isChecked() + "");
            Log.d(TAG, gson.toJson(s));
            Log.d(TAG, gson.toJson(n));

            String ss = gson.toJson(s);
            Student student = gson.fromJson(ss, Student.class);
            Log.d(TAG, student.toString());

            GenericStudent<Double> genericStudent = new GenericStudent<>(-3.4);

            Type studentType = new TypeToken<GenericStudent<Double>>() {}.getType();
            String json = gson.toJson(genericStudent, studentType);

            GenericStudent<Double> student1 = gson.fromJson(json, studentType);

            Log.e(TAG, student1.toString());
        }
    }

    public void launchContacts(View v) {
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.DialtactsContactsEntryActivity"));
        i.setAction("android.intent.action.MAIN");
        i.addCategory("android.intent.category.LAUNCHER");
        i.addCategory("android.intent.category.DEFAULT");
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String phoneNum = data.getStringExtra("phone");
                EditText phoneEdit = (EditText) findViewById(R.id.editText);
                phoneEdit.setText(phoneNum);
            }
        }
    }
}
