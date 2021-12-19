package com.example.app.global;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.app.common.bean.Song;
import com.example.app.dao.SongDaoHelper;
import com.example.app.util.Utility;

import java.util.List;

/**
 * <p>created by wyh in 2021/12/10</p>
 */
public class GlobalApp extends Application {
    private static final String TAG = GlobalApp.class.getSimpleName();
    private static GlobalApp singleton;

    public static GlobalApp getInstance() {
        return singleton;
    }

    public GlobalApp() {
        super();
        singleton = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDao();
    }

    private void initDao() {
        SongDaoHelper songDaoHelper = SongDaoHelper.getInstance(this);
        List<Song> songsDataBase = songDaoHelper.searchAll();
        String jsonStr = Utility.getJson(this, "songs.json");
        List<Song> songsData = Utility.jsonToBean(jsonStr, Song.class);
        if (songsDataBase.size() == 0) {
            addSongs(songDaoHelper, songsData);
            return;
        }


        for (int i = 0; i < songsData.size(); i++) {
            if (!songsData.get(i).toString().equals(songsDataBase.get(i).toString())) {
                songDaoHelper.insertOrReplace(new Song().setSongName(songsData.get(i).getSongName())
                        .setSongMode(songsData.get(i).getSongMode())
                        .setSongModeDetail(songsData.get(i).getSongModeDetail())
                        .setDifficulty(songsData.get(i).getDifficulty())
                        .setImageSrc(songsData.get(i).getImageSrc())
                        .setUrlPath(songsData.get(i).getUrlPath())
                        .setDescription(songsData.get(i).getDescription()));
            }
        }
    }

    private void addSongs(SongDaoHelper songDaoHelper, List<Song> songs) {


        for (Song song : songs) {
            songDaoHelper.insertOrReplace(new Song().setSongName(song.getSongName())
                    .setSongMode(song.getSongMode())
                    .setSongModeDetail(song.getSongModeDetail())
                    .setDifficulty(song.getDifficulty())
                    .setImageSrc(song.getImageSrc())
                    .setUrlPath(song.getUrlPath())
                    .setDescription(song.getDescription()));
        }
    }


    public static String getResString(int resId) {
        String string = "";

        if (null == singleton) {
            Log.e(TAG, "getResString failed, because application is null.");
            return string;
        }

        Context context = singleton.getApplicationContext();
        if (null == context) {
            Log.e(TAG, "getResString failed, because context is null.");
            return string;
        }

        Resources resources = context.getResources();
        if (null == resources) {
            Log.e(TAG, "getResString failed, because resources is null.");
            return string;
        }

        try {
            string = resources.getString(resId);
        } catch (Exception e) {
            Log.e(TAG, "getResString failed, because Resources.getString() method cause an exception.");
        }

        return string;
    }
}
