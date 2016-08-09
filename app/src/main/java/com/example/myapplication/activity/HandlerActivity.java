package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utility.RestService;

public class HandlerActivity extends Activity {

    TextView text;
    ProgressBar bar;

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle result = msg.getData();
            text.setText(result.getString("text"));
        }
    };

    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (bar.getVisibility() == View.INVISIBLE)
                bar.setVisibility(View.VISIBLE);
            bar.setProgress(msg.arg1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hander);

        text = (TextView) findViewById(R.id.textView4);
        bar = (ProgressBar) findViewById(R.id.progressBar);

        Intent i = new Intent(this, RestService.class);
        i.putExtra("messenger", new Messenger(handler1));
        startService(i);
    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Message msg = Message.obtain();
                msg.arg1 = i;
                handler2.sendMessage(msg);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startProgress(View v) {
        Thread thread = new Thread(new MyThread());
        thread.start();
    }
}
