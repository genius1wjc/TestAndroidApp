package com.example.myapplication;

/**
 * Created by jiechao on 6/23/16.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;

public class MyResultReceiver extends ResultReceiver {
    private Receiver mReceiver;

    public MyResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

    public static final Parcelable.Creator<MyResultReceiver> CREATOR
            = new Parcelable.Creator<MyResultReceiver>() {
        public MyResultReceiver createFromParcel(Parcel in) {
            return new MyResultReceiver(new Handler());
        }

        public MyResultReceiver[] newArray(int size) {
            return new MyResultReceiver[size];
        }
    };
}
