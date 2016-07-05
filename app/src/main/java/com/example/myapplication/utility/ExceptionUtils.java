package com.example.myapplication.utility;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by jiechao on 6/27/16.
 */
public class ExceptionUtils {

    /**
     * Return an exception's stack trace as a string
     * @param e
     * @return
     */
    public static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }
}
