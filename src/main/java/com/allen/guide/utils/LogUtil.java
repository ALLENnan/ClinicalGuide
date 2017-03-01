package com.allen.guide.utils;

import android.util.Log;

/**
 * @author Allen
 * @brief log util
 * @date 17/2/27
 */
public class LogUtil {
    private static final boolean DEBUG = true;
    private static final String DEFAULT_TAG = "Allen";

    private LogUtil() {
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void v(String msg) {
        if (DEBUG) {
            Log.v(DEFAULT_TAG, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(DEFAULT_TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(DEFAULT_TAG, msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            Log.w(DEFAULT_TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(DEFAULT_TAG, msg);
        }
    }
}
