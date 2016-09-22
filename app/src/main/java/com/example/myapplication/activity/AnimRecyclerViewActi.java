package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.AnimAdapter;
import com.example.myapplication.R;

import java.util.Arrays;
import java.util.List;

public class AnimRecyclerViewActi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rlist);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        List<String> items = Arrays.asList("normal text", "Cap text", "s", "this is very very very very very long",
                "normal text", "Cap text", "s", "this is very very very very very long",
                "normal text", "Cap text", "s", "this is very very very very very long",
                "normal text", "Cap text", "s", "this is very very very very very long",
                "normal text", "Cap text", "s", "this is very very very very very long",
                "normal text", "Cap text", "s", "this is very very very very very long",
                "normal text", "Cap text", "s", "this is very very very very very long");

        AnimAdapter adapter = new AnimAdapter(items, this);
        recyclerView.setAdapter(adapter);
    }
}
