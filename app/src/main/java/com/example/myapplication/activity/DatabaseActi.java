package com.example.myapplication.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.myapplication.DatabaseContract;
import com.example.myapplication.R;

public class DatabaseActi extends AppCompatActivity {

    private static final String TAG = "DBActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        Cursor resultSet = null;

        try {
            SQLiteDatabase myDB = openOrCreateDatabase(DatabaseContract.DB_NAME, MODE_PRIVATE, null);
            myDB.execSQL("create table if not exists " + DatabaseContract.StudentEntry.TABLE_NAME
                    + "(" + DatabaseContract.StudentEntry.COLUMN_NAME_ID + " integer primary key autoincrement, "
                    + DatabaseContract.StudentEntry.COLUMN_NAME_NAME + " varchar, "
                    + DatabaseContract.StudentEntry.COLUMN_NAME_GRADE + " integer)");
            myDB.execSQL("insert into Students (Name, Grade) VALUES ('Jiechao','4')");
            myDB.execSQL("insert into Students (Name, Grade) VALUES ('Nancy','2')");

            resultSet = myDB.rawQuery("Select * from Students", null);
            Log.i(TAG, "Results count is " + resultSet.getCount());
            while (resultSet.moveToNext()) {
                String name = resultSet.getString(1);
                String grade = resultSet.getString(2);
                Log.i(TAG, "name is " + name + " grade is " + grade);
            }

        } catch(Exception e) {
                e.printStackTrace();
        } finally {
            if (resultSet != null)
                resultSet.close();
        }
    }
}
