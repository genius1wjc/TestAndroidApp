package com.example.myapplication.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.threading.DownloadImageTask;
import com.example.myapplication.R;
import com.example.myapplication.utility.ImageCacheUtils;

/**
 * Caches bitmap image
 */
public class CacheActivity extends Activity {

    DownloadImageTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bitmap bitmap = ImageCacheUtils.getBitmapFromMemCache(ImageCacheUtils.IMG_KEY);
        ImageView image = (ImageView) findViewById(R.id.imageView3);

        if (bitmap == null) {
            Object retObject = getLastNonConfigurationInstance();
            if (retObject != null && retObject instanceof DownloadImageTask) {
                task = (DownloadImageTask) retObject;
                task.attach(this);
            }
            else {
                task = new DownloadImageTask(R.id.imageView3, this);
                task.execute("http://www.flooringvillage.co.uk/ekmps/shops/flooringvillage/images/request-a-sample--547-p.jpg");
            }
        }
        else
            image.setImageBitmap(bitmap);

        // Load bitmap again. Right now we have ensured that this is non-null
        bitmap = ImageCacheUtils.getBitmapFromMemCache(ImageCacheUtils.IMG_KEY);
        setBitmaps(bitmap, R.id.imageButton, R.id.imageView4, R.id.imageView5);

    }

    private void setBitmaps(Bitmap bitmap, int... ids) {
        for (int id : ids) {
            ImageView image = (ImageView) findViewById(id);
            if (image != null)
                image.setImageBitmap(bitmap);
        }
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        // If there is already bitmap in memory cache, there is no task
        if (task != null) {
            task.detach();
            return (task);
        }

        return null;
    }

}
