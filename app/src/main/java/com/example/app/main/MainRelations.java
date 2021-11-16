package com.example.app.main;

import static com.example.app.main.MainActivity.INDEX_LIGHT_MODE_DAY;
import static com.example.app.main.MainActivity.INDEX_LIGHT_MODE_NIGHT;
import static com.example.app.main.MainActivity.INDEX_NONE;

import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.app.base.activity.ActivityLifecycleListener;
import com.example.app.base.activity.BaseExternalRelations;
import com.example.app.util.DayNightManager;

/**
 * created by wyh in 2021/11/15
 */
public class MainRelations extends BaseExternalRelations<MainActivity> {

    private static final String TAG = "MainRelations";
    private DayNightManager dayNightManager;

    public MainRelations(MainActivity activity) {
        super(activity);
        dayNightManager = new DayNightManager(activity);
        activity.setOnItemEventListener((tag, switchValue, data) -> {
            switch (Integer.valueOf(tag)) {
                case INDEX_NONE:
                    //goMapActivity();
                    break;
                case INDEX_LIGHT_MODE_NIGHT:
                    dayNightManager.setDayNightMode(false);
                    break;
                case INDEX_LIGHT_MODE_DAY:
                    dayNightManager.setDayNightMode(true);
                    break;
                default:
                    break;
            }

        });
    }

    @Override
    protected ActivityLifecycleListener newActivityLifecycleListener() {
        return new ActivityLifecycleListener() {
            @Override
            public void onResume() {
                super.onResume();
            }
        };
    }
}
