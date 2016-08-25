package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class PicassoActi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        //Initialize ImageView
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Picasso.with(this)
                .load("http://www.pablo-ruiz-picasso.net/images/works/223.jpg")
                .placeholder(R.drawable.logo) // Shown before image is loaded
                .error(R.drawable.cloud) // Shown if there is an error
                .resize(250, 250)
                .centerCrop()
                .rotate(90)
                .into(imageView);
    }
}
