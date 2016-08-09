package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Team;
import com.example.myapplication.TeamListViewAdapter;

import java.util.ArrayList;

public class CustomViewActi extends AppCompatActivity {
    ArrayList<Team> myTeams;
    TeamListViewAdapter myAdapter;
    ListView myListView;
    Button myButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        myTeams = new ArrayList<Team>();
        // Add a few teams to display.
        myTeams.add(new Team("Winners", 10));
        myTeams.add(new Team("Philidelphia Flyers", 5));
        myTeams.add(new Team("Detroit Red Wings", 1));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        myListView = (ListView) findViewById(R.id.myListView);
        myButton = (Button) findViewById(R.id.buttonStart);
        // Construct our adapter, using our own layout and myTeams
        myAdapter = new TeamListViewAdapter(this, R.layout.row_team_layout, myTeams);
        myListView.setAdapter(myAdapter);
        myListView.setItemsCanFocus(false);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Team> selectedTeams = new ArrayList<>();
                final SparseBooleanArray checkedItems = myListView.getCheckedItemPositions();
                int checkedItemsCount = checkedItems.size();
                for (int i = 0; i < checkedItemsCount; ++i) {
                    // Item position in adapter
                    int position = checkedItems.keyAt(i);
                    // Add team if item is checked == TRUE!
                    if (checkedItems.valueAt(i))
                        selectedTeams.add(myAdapter.getItem(position));
                }
                if (selectedTeams.size() < 2)
                    Toast.makeText(getBaseContext(), "Need to select two or more teams.", Toast.LENGTH_SHORT).show();
                else {
                    // Just logging the output.
                    for (Team t : selectedTeams)
                        Log.d("SELECTED TEAMS: ", t.getTeamName());
                }
            }
        });

    }
}
