package com.example.myapplication.module;

import android.os.Handler;

/**
 * Created by jiechao on 9/25/16.
 */

public class MyCustomObject {
    // Step 1 - This interface defines the type of messages I want to communicate to my owner
    public interface MyCustomObjectListener {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        void onObjectReady(String title);

        // Or when data has been loaded
        void onDataLoaded(int dataType);
    }

    // Step 2 - This variable represents the listener passed in by the owning object
    // The listener must implement the events interface and passes messages up to the parent.
    private MyCustomObjectListener listener;

    // Constructor where listener events are ignored
    public MyCustomObject() {
        // set null or default listener or accept as argument to constructor
        listener = null;
        loadDataAsync();
    }

    // Assign the listener implementing events interface that will receive the events
    public void setCustomObjectListener(MyCustomObjectListener listener) {
        this.listener = listener;
    }

    private void loadDataAsync() {
        /*AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://mycustomapi.com/data/get.json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Networking is finished loading data, data is processed
                SomeData data = SomeData.processData(response.get("data"));
                // Do some other stuff as needed....
                // Now let's trigger the event
                if (listener != null)
                    listener.onDataLoaded(data); // <---- fire listener here
            }
        });*/

        Runnable task = new Runnable() {
            @Override
            public void run() {
                if (listener != null)
                    listener.onDataLoaded(1);
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(task, 3000);
    }
}
