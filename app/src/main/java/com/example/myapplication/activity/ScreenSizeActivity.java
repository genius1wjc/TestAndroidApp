package com.example.myapplication.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.example.myapplication.R;

public class ScreenSizeActivity extends Activity {

    private static final String TAG = "ScreenSizeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_size);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String text = "";
        switch(metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                text = "Low density";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                text = "Medium density";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                text = "High density";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                text = "Extra High density";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                text = "Extra Extra High density";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                text = "Extra Extra Extra High density";
                break;
        }

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Toast.makeText(this, "Screen width is " + size.x + " and height is " + size.y, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged (Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "Called");
    }
}
