package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.example.myapplication.R;

public class ViewAnimationActi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        ImageButton button = (ImageButton) findViewById(R.id.imageButton3);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.scale_rotate);
        button.startAnimation(animation1);

        RatingBar bar = (RatingBar) findViewById(R.id.ratingBar);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.alpha_translate);
        bar.startAnimation(animation2);
    }
}
