package com.example.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.myapplication.R;

public class WakeLockActi extends AppCompatActivity {

    PowerManager.WakeLock wakeLock;
    RelativeLayout layout;

    // Different Levels Wake Locks:
    // FULL_WAKE_LOCK -  Keep the screen at full brightness, keyboard back-light illuminated, and the CPU running.
    // SCREEN_BRIGHT_WAKE_LOCK - Keeps the screen at full brightness and the CPU running.
    // SCREEN_DIM_WAKE_LOCK - Keeps the screen ON but lets it dim and the CPU running.
    // PARTIAL_WAKE_LOCK -  Keeps the CPU running

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_lock);
        layout = (RelativeLayout) findViewById(R.id.layout);
    }

    public void acquireWakeLock(View v) {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
                "My wakelook");
        wakeLock.acquire();
        Snackbar.make(layout, "Wake Lack On", Snackbar.LENGTH_SHORT).show();

    }

    public void releaseWakelock(View v) {
        if (wakeLock != null) {
            wakeLock.release();
            Snackbar.make(layout, "Wake Lack Off", Snackbar.LENGTH_SHORT).show();
        }
    }
}
