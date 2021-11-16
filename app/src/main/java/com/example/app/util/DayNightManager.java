package com.example.app.util;

import android.app.Activity;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.app.R;

/**
 * created by wyh in 2021/11/15
 */
public class DayNightManager {
    private Activity context;

    public DayNightManager(Activity context) {
        this.context = context;
    }

    //切换日夜模式
    public void setDayNightMode(boolean isDayMode) {
        if (isDayMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        context.getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        context.recreate();

    }
}
