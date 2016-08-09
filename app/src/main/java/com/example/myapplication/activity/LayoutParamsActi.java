package com.example.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.TextView;

public class LayoutParamsActi extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private LayoutParams params1;
    private LayoutParams params2;
    private TextView tv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.GREEN);
        layout.setOrientation(LinearLayout.VERTICAL);

        SeekBar bar = new SeekBar(this);
        bar.setOnSeekBarChangeListener(this);
        layout.addView(bar, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        params1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params1.gravity = Gravity.LEFT | Gravity.TOP;
        params1.leftMargin = 50;

        tv = new TextView(this);
        tv.setText("Text");
        tv.setTextSize(20);
        tv.setTextColor(Color.RED);
        tv.setBackgroundColor(Color.YELLOW);
        tv.setLayoutParams(params1);
        layout.addView(tv);

        params2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.LEFT | Gravity.BOTTOM;

        button = new Button(this);
        button.setText("Button");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout, "Clicked", Snackbar.LENGTH_LONG).show();
            }
        });
        layout.addView(button, params2);

        setContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int leftValue = progress;
        int rightValue = seekBar.getMax() - progress;
        // configure the weight
        params1.weight = leftValue;
        params2.weight = rightValue;
        // write the value of variables into the buttons text
        tv.setText(String.valueOf(leftValue));
        button.setText(String.valueOf(rightValue));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
