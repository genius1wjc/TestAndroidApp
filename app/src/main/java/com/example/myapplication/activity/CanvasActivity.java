package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.CanvasView;
import com.example.myapplication.R;

public class CanvasActivity extends AppCompatActivity {

    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

}
