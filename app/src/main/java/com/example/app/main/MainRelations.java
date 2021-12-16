package com.example.app.main;

import static com.example.app.web.WebActivity.WEB_URL_KEY;

import android.content.Intent;

import com.example.app.R;
import com.example.app.base.activity.BaseExternalRelations;
import com.example.app.common.bean.Song;
import com.example.app.dao.SongDaoHelper;
import com.example.app.manager.DayNightManager;
import com.example.app.manager.NavigationBarManager;
import com.example.app.web.WebActivity;

import java.util.List;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainRelations extends BaseExternalRelations<MainActivity> {

    private static final String TAG = "MainRelations";
    private DayNightManager dayNightManager;
    private NavigationBarManager navigationBarTopManager;
    private SongDaoHelper songDaoHelper;

    public MainRelations(MainActivity activity) {
        super(activity);
        songDaoHelper = SongDaoHelper.getInstance(activity);
        List<Song> songs = songDaoHelper.searchAll();
        dayNightManager = new DayNightManager(activity);
        navigationBarTopManager = new NavigationBarManager(activity.navigationBarTop);
        navigationBarTopManager.setTitle(R.string.navigation_title_all_song);
        navigationBarTopManager.setLeftOnClickListener(v -> {

        });
        navigationBarTopManager.setRightOnClickListener(v -> {

        });
        activity.setOnItemEventListener((tag, switchValue, data) -> {
            for (Song song : songs) {
                if (song.getId() == tag) {
                    Intent intent = new Intent(activity, WebActivity.class);
                    intent.putExtra(WEB_URL_KEY, song.getUrlPath());
                    activity.startActivity(intent);
                }
            }
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
