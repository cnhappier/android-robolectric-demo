package com.example.library;

import android.util.Log;

/**
 * Created by Rollin on 2015/10/27.
 */
public class TestUtils {

    private static final String TAG = "TestUtils";

    public static String hello(){
        String msg = "hello world";
        Log.i(TAG, msg);
        return msg;
    }
}
