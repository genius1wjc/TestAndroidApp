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

        Picasso.with(this).setIndicatorsEnabled(true); // 令Picasso在其图像上加上标记，用于辨识图像缓存状态
        // 图片左上角的蓝色角标表示图像已缓存到本地存储空间（disk），绿色表示图像位于内存（memory），红色表示图像位于网络（network）
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);

        Picasso.with(this)
                .load("http://www.pablo-ruiz-picasso.net/images/works/223.jpg")
                .placeholder(R.drawable.logo) // Shown before image is loaded
                .error(R.drawable.cloud) // Shown if there is an error
                .resize(250, 250)
                .centerCrop()
                .rotate(90)
                .into(imageView);

        Picasso.with(this)
                .load("http://www.pablo-ruiz-picasso.net/images/works/223.jpg")
                .centerCrop()
                .resizeDimen(R.dimen.preview_image_width, R.dimen.preview_image_height)
                .fetch(); // Use fetch() to pre-fetch images
    }
}
