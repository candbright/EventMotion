package com.example.app.main;

import static com.example.app.main.MainActivity.INDEX_LIGHT_MODE_DAY;
import static com.example.app.main.MainActivity.INDEX_LIGHT_MODE_NIGHT;
import static com.example.app.main.MainActivity.INDEX_GO_ACTIVITY_REGISTER;

import android.content.Intent;

import com.example.app.base.activity.BaseExternalRelations;
import com.example.app.login.LoginActivity;
import com.example.app.manager.DayNightManager;

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
                case INDEX_GO_ACTIVITY_REGISTER:
                    Intent registerIntent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(registerIntent);
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
}
