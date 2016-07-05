package com.example.myapplication;

import android.net.Uri;

/**
 * Created by jiechao on 7/5/16.
 */
public class CommonConstants {

    public static final String PROVIDER_NAME = "android.content.provider.images";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/images");
}
