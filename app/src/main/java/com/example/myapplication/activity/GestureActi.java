package com.example.myapplication.activity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.myapplication.R;

public class GestureActi extends AppCompatActivity {

    private static final String TAG = "GestureActi";

    private ScaleGestureDetector sgd;
    private GestureDetector gd;
    private ImageView iv;
    private Matrix matrix = new Matrix();
    private float scale = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        sgd = new ScaleGestureDetector(this, new ScaleListener());
        gd = new GestureDetector(this, new Gesture());
        iv = (ImageView) findViewById(R.id.imageView);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "Touched");
        sgd.onTouchEvent(ev);
        gd.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d(TAG, "x is " + detector.getFocusX());
            Log.d(TAG, "y is " + detector.getFocusY());
            Log.d(TAG, "Previous span is " + detector.getPreviousSpan());

            // Get scaling factor from the previous scale event to the current event.
            scale *= detector.getScaleFactor();
            // Ensure the scale factor is between 0.1f to 5.0f
            scale = Math.max(0.1f, Math.min(scale, 5.0f));

            matrix.setScale(scale, scale);
            iv.setImageMatrix(matrix);
            return true;
        }
    }

    private class Gesture extends GestureDetector.SimpleOnGestureListener {
        public boolean onSingleTapUp(MotionEvent ev) {
            Log.d(TAG, "Single tap: x = " + ev.getX());
            return false;
        }

        public void onLongPress(MotionEvent ev) {
            Log.d(TAG, "Long press");
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "Scroll with distance " + distanceX + " and " + distanceY);
            return false;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "Fling with velocity " + velocityX + " and " + velocityY);
            return false;
        }
    }
}
