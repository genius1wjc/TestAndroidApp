package com.example.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class HandlerThreadActi extends Activity implements Handler.Callback {

    public class CustomHandlerThread extends HandlerThread implements Handler.Callback {
        public static final int MSG_FINISHED = 100;
        public static final int MSG_COUNT_UP = 101;
        public static final int MSG_COUNT_DOWN = 102;

        private Handler handler, callback;

        public CustomHandlerThread(String name) {
            super(name);
            // TODO Auto-generated constructor stub
        }

        @SuppressWarnings("unused")
        public CustomHandlerThread(String name, int priority) {
            super(name, priority);
            // TODO Auto-generated constructor stub
        }

        public void setCallback(Handler cb) {
            callback = cb;
        }

        @Override
        protected void onLooperPrepared() {
            handler = new Handler(getLooper(), this);
        }

        @Override
        public boolean handleMessage(Message msg) {

            int data1 = msg.arg1;
            int data2 = msg.arg2;
            int counter;

            switch (msg.what) {
                case MSG_COUNT_UP:
                    for (counter = data1; counter < data2; counter++) {
                        //...
                    }
                    callback.sendMessage(Message.obtain(null, MSG_FINISHED, counter));
                    break;
                case MSG_COUNT_DOWN:
                    for (counter = data1; counter > data2; counter--) {
                        //...
                    }
                    callback.sendMessage(Message.obtain(null, MSG_FINISHED, counter));
                    break;
            }
            return true;
        }

        public void querySomething(int start, int end) {
            if (start > end) {
                Message msg = Message.obtain(
                        null, //Handler h
                        MSG_COUNT_DOWN, //int what
                        start, //int arg1
                        end); //int arg2
                handler.sendMessage(msg);
            } else if (start < end) {
                Message msg = Message.obtain(
                        null, //Handler h
                        MSG_COUNT_UP, //int what
                        start, //int arg1
                        end);  //int arg2
                handler.sendMessage(msg);
            }
        }
    }

    private final static String MyName = "My CustomHandlerThread";

    private CustomHandlerThread myCustomHandlerThread;

    private Handler myHandler;

    Button btnUp, btnDown;

    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        btnUp = (Button) findViewById(R.id.buttonup);
        btnDown = (Button) findViewById(R.id.buttondown);
        textResult = (TextView) findViewById(R.id.result);

        btnUp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myCustomHandlerThread.querySomething(1, 100);
            }
        });

        btnDown.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myCustomHandlerThread.querySomething(500, 10);
            }
        });

        myHandler = new Handler(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        myCustomHandlerThread = new CustomHandlerThread(MyName);
        myCustomHandlerThread.setCallback(myHandler);
        myCustomHandlerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        myCustomHandlerThread.setCallback(null);
        myCustomHandlerThread.quit();
        myCustomHandlerThread = null;
    }

    @Override
    public boolean handleMessage(Message arg0) {
        int result = (Integer) arg0.obj;
        textResult.setText("result: " + result);
        return false;
    }
}