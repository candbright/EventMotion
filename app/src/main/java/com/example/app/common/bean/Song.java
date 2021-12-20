package com.example.app.common.bean;

import com.example.app.dao.DaoSession;
import com.example.app.dao.SongDao;
import com.example.app.dao.StringConverter;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import com.example.app.dao.SongCommentDao;

/**
 * <p>created by wyh in 2021/12/13</p>
 */
@Entity
public class Song {
    public static final String MODE_FANCY = "MODE_FANCY";       //花式
    public static final String MODE_RACE = "MODE_RACE";       //竞速

    public static final String STUDY = "STUDY";       //学习
    public static final String PERFORM = "PERFORM";     //表演
    public static final String INSANE = "INSANE";      //疯狂
    public static final String DOUBLE = "DOUBLE";      //双踏板
    public static final String LOVER = "LOVER";      //情侣
    public static final String REMIX = "REMIX";       //混音
    @Id(autoincrement = true)
    private Long id;
    //歌曲名称
    private String songName;
    //歌曲所在模式
    private String songMode;
    //歌曲具体模式
    private String songModeDetail;
    //歌曲难度
    private int difficulty;
    //歌曲标题
    private String title;
    //歌曲封面路径
    private String imageSrc;
    //歌曲链接路径
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> urlPath;
    //歌曲描述
    private String description;
    //歌曲常见键位类型
    //private List<Integer> footMode;
    //评论
    @ToMany(referencedJoinProperty = "songId")
    private List<SongComment> comments;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1369727947)
    private transient SongDao myDao;

    @Generated(hash = 87031450)
    public Song() {
    }


    @Generated(hash = 1277375759)
    public Song(Long id, String songName, String songMode, String songModeDetail,
                int difficulty, String title, String imageSrc, List<String> urlPath,
                String description) {
        this.id = id;
        this.songName = songName;
        this.songMode = songMode;
        this.songModeDetail = songModeDetail;
        this.difficulty = difficulty;
        this.title = title;
        this.imageSrc = imageSrc;
        this.urlPath = urlPath;
        this.description = description;
    }


    public String getSongName() {
        return songName;
    }

    public Song setSongName(String songName) {
        this.songName = songName;
        return this;
    }

    public String getSongMode() {
        return songMode;
    }

    public Song setSongMode(String songMode) {
        this.songMode = songMode;
        return this;
    }

    public boolean isFancyMode() {
        return songMode.equals(MODE_FANCY);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Song setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<String> getUrlPath() {
        return urlPath;
    }

    public Song setUrlPath(List<String> urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Song setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }

    public Song setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
        return this;
    }

    @Keep
    public List<SongComment> getComments() {
        return comments;
    }

    @Keep
    public Song setComments(List<SongComment> comments) {
        this.comments = comments;
        return this;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 249603048)
    public synchronized void resetComments() {
        comments = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 767980484)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSongDao() : null;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", songMode='" + songMode + '\'' +
                ", songModeDetail='" + songModeDetail + '\'' +
                ", difficulty=" + difficulty +
                ", title='" + title + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                ", urlPath=" + urlPath +
                ", description='" + description + '\'' +
                '}';
    }

    public String getSongModeDetail() {
        return this.songModeDetail;
    }


    public Song setSongModeDetail(String songModeDetail) {
        this.songModeDetail = songModeDetail;
        return this;
    }
}
