package com.example.app.util;

import com.example.app.global.GlobalApp;

/**
 * <p>created by wyh in 2021/12/14</p>
 */
public class Utility {
    public static float dip2px(float dpValue) {
        final float scale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

    public static float px2dip(float pxValue) {
        final float scale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        return pxValue / scale + 0.5f;
    }

    public static int px2sp(float pxValue) {
        final float fontScale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float dpValue) {
        final float fontScale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (dpValue * fontScale + 0.5f);
    }

    public static int dip2sp(float dpValue) {
        final float scale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        final float fontScale = GlobalApp.getInstance().getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) ((dpValue * scale + 0.5f) / fontScale + 0.5f);
    }
}
