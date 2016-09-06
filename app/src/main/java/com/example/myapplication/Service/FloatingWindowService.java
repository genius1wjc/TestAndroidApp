package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

public class FloatingWindowService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
                // Shrink the window to wrap the content rather than filling the screen
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                // Display it on top of other application windows, but only for the current user
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                // Don't let it grab the input focus
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                // Make the underlying application window visible through any transparent parts
                PixelFormat.TRANSLUCENT);

        // Define the position of the window within the screen
        p.gravity = Gravity.TOP | Gravity.RIGHT;
        p.x = 0;
        p.y = 100;

        TextView tv = new TextView(this);
        tv.setText("Floating");
        tv.setBackgroundResource(android.R.color.holo_red_light);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        try {
            windowManager.addView(tv, p);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Compatibility version of START_STICKY that does not guarantee
        // that onStartCommand(Intent, int, int) will be called again after being killed
        return START_STICKY_COMPATIBILITY;
    }
}
