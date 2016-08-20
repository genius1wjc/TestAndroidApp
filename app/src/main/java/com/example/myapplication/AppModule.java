package com.example.myapplication;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiechao on 8/20/16.
 */
@Module(
    injects = {
        MyApplication.class
    },
    library = true
)
public class AppModule {

    private MyApplication app;

    public AppModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    public AnalyticsManager provideAnalyticsManager(){
        return new AnalyticsManager(app);
    }
}
