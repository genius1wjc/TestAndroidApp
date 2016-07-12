package com.example.myapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;

public class BroadcastActi extends AppCompatActivity {

    // The Intent namespace is global. Make sure that Intent action names and other strings
    // are written in a namespace you own, or else you may inadvertently conflict with other applications.
    private static final String ACTION = "com.example.myapplication.action.CUSTOM_INTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(ACTION));
    }

    public void sendBroadcast(View v) {
        Intent intent = new Intent();
        intent.setAction(ACTION);
        Bundle b = new Bundle();
        b.putChar("char", 'a');
        b.putFloat("float", 11.0f);
        b.putDouble("double", 11.0);
        intent.putExtra("bundle", b);
        // Do not need to do this for broadcast sent by LocalBroadcastManager
        //intent.setPackage("com.example.myapplication");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        Toast.makeText(this, "Broadcast sent", Toast.LENGTH_LONG).show();
    }

    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getBundleExtra("bundle");
            char c = b.getChar("char");
            float f = b.getFloat("float");
            double d = b.getDouble("double");

            Toast.makeText(context, "char is " + c + " ,float is " + f + " and double is " + d, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

}
