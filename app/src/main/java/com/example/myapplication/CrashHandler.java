package com.example.myapplication;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Created by jiechao on 8/7/16.
 */

public class CrashHandler implements UncaughtExceptionHandler{

    private static final String TAG = CrashHandler.class.getSimpleName();

    private static CrashHandler crashHandler;

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            ex.printStackTrace(pw);
            Log.e(TAG, sw.toString());
        } finally {
            if (pw != null)
                pw.close();
        }

        // TODO Auto-generated method stub
        if (crashHandler != null) {
            try {
                //将crash log写入文件
                FileOutputStream fileOutputStream = new FileOutputStream("/mnt/sdcard/crash_log.txt", true);
                PrintStream printStream = new PrintStream(fileOutputStream);
                ex.printStackTrace(printStream);
                printStream.flush();
                printStream.close();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //设置默认处理器
    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private CrashHandler() {}

    //单例
    public static CrashHandler instance() {
        if (crashHandler == null)
            crashHandler = new CrashHandler();
        return crashHandler;
    }
}
