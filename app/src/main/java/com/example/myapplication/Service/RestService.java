package com.example.myapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

/**
 *
 */
public class RestService extends IntentService {

    private static final String TAG = "RestService";

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    public RestService() {
        super("Rest service");
    }

    protected void onHandleIntent(Intent intent) {
        Bundle b = new Bundle();

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String command = intent.getStringExtra("command");

        if ("query".equals(command)) {
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);
            try {
                // get some data or something
                b.putString(Intent.EXTRA_TEXT, "a result");
                receiver.send(STATUS_FINISHED, b);
            } catch(Exception e) {
                b.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, b);
            }
        }

        // Sends a message back to HandlerActivity as a way to update UI
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Messenger messenger = (Messenger) bundle.get("messenger");
            Message msg = Message.obtain();
            b.putString("text", "Text Updated From RestService");
            msg.setData(b);

            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        }
    }
}
