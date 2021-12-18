package com.example.app.main;

import android.content.Intent;

import com.example.app.R;
import com.example.app.base.activity.BaseExternalRelations;
import com.example.app.common.bean.Song;
import com.example.app.dao.SongDaoHelper;
import com.example.app.manager.NavigationBarManager;
import com.example.app.web.WebActivity;

import java.util.List;

import static com.example.app.common.bean.Song.DOUBLE;
import static com.example.app.common.bean.Song.INSANE;
import static com.example.app.common.bean.Song.LOVER;
import static com.example.app.common.bean.Song.MODE_FANCY;
import static com.example.app.common.bean.Song.MODE_RACE;
import static com.example.app.common.bean.Song.PERFORM;
import static com.example.app.common.bean.Song.REMIX;
import static com.example.app.common.bean.Song.STUDY;
import static com.example.app.main.MainActivity.MODE_ALL;
import static com.example.app.main.MainActivity.MODE_DETAIL_ALL;
import static com.example.app.web.WebActivity.WEB_URL_KEY;

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
        activity.selectorBar.allDetailBtn.setOnClickListener(v -> {
            List<Song> raceSong = songDaoHelper.searchByModeAndDetail(activity.mode, MODE_DETAIL_ALL);
            activity.setData(raceSong);
            activity.modeDetail = MODE_DETAIL_ALL;
            activity.matchModeDetail();
        });
        activity.selectorBar.studyDetailBtn.setOnClickListener(v -> {
            List<Song> raceSong = songDaoHelper.searchByModeAndDetail(activity.mode, STUDY);
            activity.setData(raceSong);
            activity.modeDetail = STUDY;
            activity.matchModeDetail();
        });
        activity.selectorBar.performDetailBtn.setOnClickListener(v -> {
            List<Song> raceSong = songDaoHelper.searchByModeAndDetail(activity.mode, PERFORM);
            activity.setData(raceSong);
            activity.modeDetail = PERFORM;
            activity.matchModeDetail();
        });
        activity.selectorBar.insaneDetailBtn.setOnClickListener(v -> {
            List<Song> raceSong = songDaoHelper.searchByModeAndDetail(activity.mode, INSANE);
            activity.setData(raceSong);
            activity.modeDetail = INSANE;
            activity.matchModeDetail();
        });
        activity.selectorBar.doubleDetailBtn.setOnClickListener(v -> {
            List<Song> raceSong = songDaoHelper.searchByModeAndDetail(activity.mode, DOUBLE);
            activity.setData(raceSong);
            activity.modeDetail = DOUBLE;
            activity.matchModeDetail();
        });
        activity.selectorBar.loverDetailBtn.setOnClickListener(v -> {
            List<Song> raceSong = songDaoHelper.searchByModeAndDetail(activity.mode, LOVER);
            activity.setData(raceSong);
            activity.modeDetail = LOVER;
            activity.matchModeDetail();
        });
        activity.selectorBar.remixDetailBtn.setOnClickListener(v -> {
            List<Song> raceSong = songDaoHelper.searchByModeAndDetail(activity.mode, REMIX);
            activity.setData(raceSong);
            activity.modeDetail = REMIX;
            activity.matchModeDetail();
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
