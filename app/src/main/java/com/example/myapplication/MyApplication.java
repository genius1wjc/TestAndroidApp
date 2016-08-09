package com.example.myapplication;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

/**
 * Created by jiechao on 7/20/16.
 */
public class MyApplication extends MultiDexApplication {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.instance();
        crashHandler.init();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        Log.w(TAG, "We are now on low memory!");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
