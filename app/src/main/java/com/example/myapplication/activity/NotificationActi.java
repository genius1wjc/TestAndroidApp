package com.example.myapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationActi extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 0;

    private NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void addNotification(View v) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // For security reason, this needs to be an explit intent
                Intent intent = new Intent(NotificationActi.this, ProfileActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActi.this, 0, intent, 0);

                nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification noti = new Notification.Builder(NotificationActi.this)
                        .setTicker("Ticker")
                        .setContentTitle("Title")
                        .setContentText("Text")
                        .setSmallIcon(R.drawable.c)
                        .setLargeIcon(BitmapFactory.decodeResource(NotificationActi.this.getResources(),
                                R.drawable.java))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();

                nm.notify(NOTIFICATION_ID, noti);
            }
        }, 0);
    }

    public void CancelNotification (View v) {
        if (nm != null)
            nm.cancel(NOTIFICATION_ID);
    }
}