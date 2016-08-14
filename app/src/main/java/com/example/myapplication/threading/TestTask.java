package com.example.myapplication.threading;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by jiechao on 6/28/16.
 */
public class TestTask extends AsyncTask<Void, Void, Boolean>{

    private Activity activity = null;
    private int id;

    public TestTask(Activity activity, int id) {
        this.activity = activity;
        this.id = id;
    }

    @Override
    protected void onPreExecute() {
        if (activity != null)
            Toast.makeText(activity, "Task " + id + " has started", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        if (activity != null)
            Toast.makeText(activity, "Task " + id + " has finished", Toast.LENGTH_SHORT).show();
    }
}
