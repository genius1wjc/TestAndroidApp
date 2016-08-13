package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.myapplication.ObservableScrollView;
import com.example.myapplication.R;
import com.example.myapplication.ScrollViewListener;

public class ScrollViewsActi extends AppCompatActivity implements ScrollViewListener{

    private ObservableScrollView sv1, sv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_scroll_views);

        sv1 = (ObservableScrollView) findViewById(R.id.sv1);
        sv2 = (ObservableScrollView) findViewById(R.id.sv2);
        sv1.setOnScrollViewListener(this);
        sv2.setOnScrollViewListener(this);
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView == sv1)
            sv2.scrollTo(x, y);
        else if (scrollView == sv2)
            sv1.scrollTo(x, y);
    }
}
