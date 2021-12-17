package com.example.app.main;

import static com.example.app.common.bean.Song.MODE_ALL;
import static com.example.app.common.bean.Song.MODE_FANCY;
import static com.example.app.common.bean.Song.MODE_RACE;
import static com.example.app.web.WebActivity.WEB_URL_KEY;

import android.content.Intent;

import com.example.app.R;
import com.example.app.base.activity.BaseExternalRelations;
import com.example.app.common.bean.Song;
import com.example.app.dao.SongDaoHelper;
import com.example.app.manager.NavigationBarManager;
import com.example.app.web.WebActivity;

import java.util.List;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainRelations extends BaseExternalRelations<MainActivity> {

    private static final String TAG = "MainRelations";
    private NavigationBarManager navigationBarTopManager;
    private SongDaoHelper songDaoHelper;

    public MainRelations(MainActivity activity) {
        super(activity);
        songDaoHelper = SongDaoHelper.getInstance(activity);
        List<Song> songs = songDaoHelper.searchAll();
        navigationBarTopManager = new NavigationBarManager(activity.navigationBarTop);
        navigationBarTopManager.setTitle(R.string.main_title);
        navigationBarTopManager.setLeftOnClickListener(v -> {

        });
        navigationBarTopManager.setRightOnClickListener(v -> {

        });
        activity.selectorBar.allModeBtn.setOnClickListener(v -> {
            List<Song> allSong = songDaoHelper.searchAll();
            activity.setData(allSong);
            activity.mode = MODE_ALL;
            activity.matchMode();
        });
        activity.selectorBar.fancyModeBtn.setOnClickListener(v -> {
            List<Song> fancySong = songDaoHelper.searchFancyMode();
            activity.setData(fancySong);
            activity.mode = MODE_FANCY;
            activity.matchMode();
        });
        activity.selectorBar.raceModeBtn.setOnClickListener(v -> {
            List<Song> raceSong = songDaoHelper.searchRaceMode();
            activity.setData(raceSong);
            activity.mode = MODE_RACE;
            activity.matchMode();
        });
        activity.setOnItemEventListener((tag, switchValue, data) -> {
            for (Song song : songs) {
                if (song.getId() == tag) {
                    Intent intent = new Intent(activity, WebActivity.class);
                    intent.putExtra(WEB_URL_KEY, song.getUrlPath().get(0));
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
