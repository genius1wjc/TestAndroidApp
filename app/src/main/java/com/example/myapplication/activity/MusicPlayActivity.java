package com.example.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.MusicPlayService;
import com.example.myapplication.R;

public class MusicPlayActivity extends AppCompatActivity {

    private static final String STATE_USER = "a key";
    private String mUser;

    private static final String TAG = "MusicPlayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            mUser = savedInstanceState.getString(STATE_USER);
        } else {
            // Probably initialize members with default values for a new instance
            mUser = "NewUser";
        }

        Log.d(TAG, mUser);
    }

    public void startPlaying(View v) {
        startService(new Intent(getBaseContext(), MusicPlayService.class));
    }

    public void stopPlaying(View v) {
        stopService(new Intent(getBaseContext(), MusicPlayService.class));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(STATE_USER, "a value");
        // Always call the superclass so it can save the view hierarchy state
    }
}
