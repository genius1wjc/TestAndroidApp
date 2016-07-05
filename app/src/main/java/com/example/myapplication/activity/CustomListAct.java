package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.myapplication.CustomAdapter;
import com.example.myapplication.R;

public class CustomListAct extends AppCompatActivity {
    ListView lv;
    public static int[] prgmImages = { R.drawable.c, R.drawable.c_plus_plus, R.drawable.java, R.drawable.javascript,
            R.drawable.c, R.drawable.c_plus_plus, R.drawable.java, R.drawable.javascript,
            R.drawable.c, R.drawable.c_plus_plus, R.drawable.java, R.drawable.javascript,
            R.drawable.c, R.drawable.c_plus_plus, R.drawable.java, R.drawable.javascript,
            R.drawable.c, R.drawable.c_plus_plus, R.drawable.java, R.drawable.javascript };
    public static String[] prgmNameList = { "Let Us C", "C++", "Java", "JavaScript",
            "Let Us C", "C++", "Java", "JavaScript",
            "Let Us C", "C++", "Java", "JavaScript",
            "Let Us C", "C++", "Java", "JavaScript",
            "Let Us C", "C++", "Java", "JavaScript" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));
    }
}
