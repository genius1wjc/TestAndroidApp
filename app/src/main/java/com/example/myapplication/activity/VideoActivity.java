package com.example.myapplication.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.myapplication.R;

public class VideoActivity extends Activity {

    private static final String TAG = "VideoActivity";

    WifiManager.WifiLock wifiLock;

    // Declare variables
    ProgressDialog pDialog;

    // VideoView is basically a wrapper of MediaPlayer and SurfaceView
    VideoView videoView;

    // Insert your Video URL
    String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";

    private static final String CURRENT_POSITION = "Video Position";

    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from video_main.xml
        setContentView(R.layout.activity_video);

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(CURRENT_POSITION);
            Log.d(TAG, "Recreating activity with current position " + currentPosition);
        }
        else
            Log.d(TAG, "New activity");

        wifiLock = ((WifiManager) getSystemService(Context.WIFI_SERVICE))
                .createWifiLock(WifiManager.WIFI_MODE_FULL, "mylock");
    }

    @Override
    protected void onPause() {
        if (videoView != null) {
            currentPosition = videoView.getCurrentPosition();
            Log.d(TAG, "onPause, getting current position " + currentPosition);
        }
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        if (videoView != null) {
            Log.d(TAG, "onSaveInstanceState, saving current position " + currentPosition);
            state.putInt(CURRENT_POSITION, currentPosition);
        }
        super.onSaveInstanceState(state);
    }

    /*@Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        currentPosition = state.getInt(CURRENT_POSITION);
        Log.d(TAG, "onRestoreInstanceState, restoring current position " + currentPosition);
    }*/

    @Override
    protected void onRestart() {
        if (videoView != null) {
            Log.d(TAG, "onRestart, using current position " + currentPosition);
            videoView.seekTo(currentPosition);
        }
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "ORIENTATION_LANDSCAPE");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d(TAG, "ORIENTATION_PORTRAIT");
        }
    }

    public void streamVideo(View v) {
        // Find your VideoView in your video_main.xml layout
        videoView = (VideoView) findViewById(R.id.VideoView);
        // Execute StreamVideo AsyncTask

        // Create a progressbar
        pDialog = new ProgressDialog(VideoActivity.this);
        // Set progressbar title
        pDialog.setTitle("Android Video Streaming");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController controller = new MediaController(VideoActivity.this);
            controller.setAnchorView(videoView);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoView.setMediaController(controller);

            videoView.setVideoURI(video);

            // Or use this to play a video from SD card
            //videoView.setVideoPath(path);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();

        videoView.setOnPreparedListener(new OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoView.start();
                wifiLock.acquire();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Snackbar.make(findViewById(android.R.id.content), "Yes you finished it!", Snackbar.LENGTH_LONG)
                        .show();

                wifiLock.release();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(TAG, "Error preparing video. " + what + extra);

                // Did not handle the error, so return false
                return false;
            }
        });
    }

}
