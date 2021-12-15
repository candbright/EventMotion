package com.example.app.dao;

import android.content.Context;

import com.example.app.common.bean.Song;

import java.util.List;

/**
 * <p>created by wyh in 2021/12/13</p>
 */
public class SongDaoHelper {
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private SongDao mSongDao;
    private static SongDaoHelper mSongDaoHelper;

    private SongDaoHelper(Context context) {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), "SONG.db", null);
        mDaoMaster = new DaoMaster(mDevOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
        mSongDao = mDaoSession.getSongDao();
    }

    public static SongDaoHelper getInstance(Context context) {
        if (mSongDaoHelper == null) {
            synchronized (SongDaoHelper.class) {
                if (mSongDaoHelper == null) {
                    mSongDaoHelper = new SongDaoHelper(context);
                }
            }
        }
        return mSongDaoHelper;
    }


    /**
     * add
     */
    public long insert(Song song) {
        return mSongDao.insert(song);
    }

    public void insertOrReplace(Song song) {
        mSongDao.insertOrReplace(song);
    }

    /**
     * delete
     */
    public void delete(String name) {
        mSongDao.queryBuilder().where(SongDao.Properties.SongName.eq(name)).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    /**
     * delete all
     */
    public void deleteAll() {
        mDaoSession.deleteAll(Song.class);
    }

    /**
     * update
     */

    public void update(Song song) {
        Song old = mSongDao.queryBuilder().where(SongDao.Properties.Id.eq(song.getId())).build().unique();
        if (old != null) {
            old.setSongName("iris");
            mSongDao.update(old);
        }
    }

    /**
     * query
     */
    public List<Song> searchByName(String songName) {
        List<Song> songs = (List<Song>) mSongDao.queryBuilder().where(SongDao.Properties.SongName.eq(songName)).build().unique();
        return songs;
    }

    public List<Song> searchById(String id) {
        List<Song> songs = (List<Song>) mSongDao.queryBuilder().where(SongDao.Properties.Id.eq(id)).build().unique();
        return songs;
    }

    public List<Song> searchAll() {
        List<Song> songs = mSongDao.queryBuilder().list();
        return songs;
    }

}
