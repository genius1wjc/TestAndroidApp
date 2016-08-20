package com.example.myapplication;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by jiechao on 7/20/16.
 */
public class MyApplication extends MultiDexApplication {

    private static final String TAG = "MyApplication";

    private ObjectGraph objectGraph;
    @Inject
    AnalyticsManager analyticsManager;

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler crashHandler = CrashHandler.instance();
        crashHandler.init();

        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
        // Dependencies are injected, so we can call AnalyticsManager method.
        analyticsManager.registerAppEnter();
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
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