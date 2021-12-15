package com.example.app.main;

import com.example.app.R;
import com.example.app.base.activity.BaseExternalRelations;
import com.example.app.manager.DayNightManager;
import com.example.app.manager.NavigationBarManager;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainRelations extends BaseExternalRelations<MainActivity> {

    private static final String TAG = "MainRelations";
    private DayNightManager dayNightManager;
    private NavigationBarManager navigationBarTopManager;

    public MainRelations(MainActivity activity) {
        super(activity);
        dayNightManager = new DayNightManager(activity);
        navigationBarTopManager = new NavigationBarManager(activity.navigationBarTop);
        navigationBarTopManager.setTitle(R.string.navigation_title_all_song);
        navigationBarTopManager.setLeftOnClickListener(v -> {

        });
        navigationBarTopManager.setRightOnClickListener(v -> {

        });
        activity.setOnItemEventListener((tag, switchValue, data) -> {

        });
        activity.setMusicButtonClickListener(v -> {

        });
        activity.setSquareButtonClickListener(v -> {

        });
        activity.setTeachButtonClickListener(v -> {

        });
        activity.setCollectButtonClickListener(v -> {

        });
    }
}
