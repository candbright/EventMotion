package com.example.app.global;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.app.R;
import com.example.app.common.bean.Song;
import com.example.app.dao.SongDaoHelper;

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
        songDaoHelper.deleteAll();
        Song baby = new Song().setSongName("baby").setSongMode(Song.MODE_RACE_INSANE).setDifficulty(4)
                .setImageSrc(R.mipmap.baby).setUrlPath("").setDescription(getResString(R.string.song_description_baby));
        songDaoHelper.insertOrReplace(baby);
        Song breakdown = new Song().setSongName("breakdown").setSongMode(Song.MODE_RACE_INSANE).setDifficulty(11)
                .setImageSrc(R.mipmap.breakdown).setUrlPath("").setDescription(getResString(R.string.song_description_breakdown));
        songDaoHelper.insertOrReplace(breakdown);
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
