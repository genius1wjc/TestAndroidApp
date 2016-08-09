package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class SwipeRefreshActi extends AppCompatActivity {

    ArrayList<String> listValues;

    ListView listView;

    ArrayAdapter<String> myAdapter;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Only ever call `setContentView` once right at the top
        setContentView(R.layout.activity_swipe_refresh);

        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        listView = (ListView) findViewById(R.id.lvItems);

        listValues = new ArrayList<>();
        listValues.add("Android");
        listValues.add("iOS");
        listValues.add("Symbian");
        listValues.add("Blackberry");
        listValues.add("Windows Phone");
        listValues.add("Nexus");

        // initiate the listadapter
        myAdapter = new ArrayAdapter<>(this,
                R.layout.row_layout, R.id.listText, listValues);

        // assign the activity_list adapter
        listView.setAdapter(myAdapter);
    }

    public void fetchTimelineAsync() {
        ArrayList<String> listCopy = new ArrayList<>();

        for (int i = 0; i < listValues.size() - 1; i++) {
            listCopy.add(listValues.get(i));
        }

        // Remember to CLEAR OUT old items before appending in the new ones
        myAdapter.clear();

        // ...the data has come back, add new items to your adapter...
        myAdapter.addAll(listCopy);

        // Now we call setRefreshing(false) to signal refresh has finished
        swipeContainer.setRefreshing(false);
    }
}
