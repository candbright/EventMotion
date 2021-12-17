package com.example.app.base.activity;

import android.util.Log;

/**
 * created by wyh in 2021/11/15
 */
public class ActivityLifecycleListener {
    private static final String TAG = "<Lifecycle>";
    private static final boolean LOGD = true;
    public void onModulesCreated() {
        if (LOGD) {
            Log.d(TAG, "onModulesCreated()");
        }
    }

    public void onResume() {
        if (LOGD) {
            Log.d(TAG, "onResume()");
        }
    }

    public void onPause() {
        if (LOGD) {
            Log.d(TAG, "onPause()");
        }
    }

    public void onDestroy() {
        if (LOGD) {
            Log.d(TAG, "onDestroy()");
        }
    }

    /**
     *返回true，则调用super方法。返回false，则不调用。
     */
    public boolean onBackPressed() {
        if (LOGD) {
            Log.d(TAG, "onBackPressed()");
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }
}