package com.example.myapplication.task;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.utility.ExceptionUtils;
import com.example.myapplication.utility.ImageCacheUtils;

import java.io.InputStream;

/**
 * Created by jiechao on 6/19/16.
 * Have made it rotation aware
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "DownloadImageTask";
    int resourceId;
    private Activity activity;

    public DownloadImageTask(int resourceId, Activity activity) {
        this.resourceId = resourceId;
        attach(activity);
    }

    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
            ImageCacheUtils.addBitmapToMemoryCache(ImageCacheUtils.IMG_KEY, bitmap);

        } catch (Exception e) {
            Log.e(TAG, ExceptionUtils.getStackTrace(e));
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        if (activity == null || isCancelled()) {
            Log.w("DownloadImageTask", "onPostExecute() skipped -- no activity or task was canceled");
        }
        else {
            if (result != null) {
                ImageView image = (ImageView) activity.findViewById(resourceId);
                image.setImageBitmap(result);
            }
            else
                Log.e(TAG, "Bitmap image is null");
        }
    }

    public void detach() {
        activity = null;
    }

    public void attach(Activity activity) {
        this.activity = activity;
    }
}