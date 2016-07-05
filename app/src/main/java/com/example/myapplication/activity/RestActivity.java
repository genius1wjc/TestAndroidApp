package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.MyResultReceiver;
import com.example.myapplication.utility.RestService;

public class RestActivity extends Activity implements MyResultReceiver.Receiver {

    public MyResultReceiver mReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReceiver = new MyResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        final Intent intent = new Intent(Intent.ACTION_SYNC, null, this, RestService.class);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("command", "query");
        startService(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        mReceiver.setReceiver(null); // clear receiver so no leaks.
    }

    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case RestService.STATUS_RUNNING:
                //show progress
                break;
            case RestService.STATUS_FINISHED:
                String result = resultData.getString(Intent.EXTRA_TEXT);
                // do something interesting
                // hide progress
                break;
            case RestService.STATUS_ERROR:
                // handle the error;
                break;
        }
    }
}
