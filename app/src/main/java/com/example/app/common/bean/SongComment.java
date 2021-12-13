package com.example.app.common.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * <p>created by wyh in 2021/12/13</p>
 */
@Entity
public class SongComment {
    @Id(autoincrement = true)
    private Long id;
    //评论歌曲的对应id
    private int songId;
    //评论内容
    private String content;
    //评论时间
    private int date;
    //点赞数
    private int likes;
    //评论对象
    private int commentId;
    @Generated(hash = 1938450999)
    public SongComment() {
    }
    @Generated(hash = 454792867)
    public SongComment(Long id, int songId, String content, int date, int likes, int commentId) {
        this.id = id;
        this.songId = songId;
        this.content = content;
        this.date = date;
        this.likes = likes;
        this.commentId = commentId;
    }
    public Long getId() {
        return id;
    }

    public SongComment setId(Long id) {
        this.id = id;
        return this;
    }

    public int getSongId() {
        return songId;
    }

    public SongComment setSongId(int songId) {
        this.songId = songId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SongComment setContent(String content) {
        this.content = content;
        return this;
    }

    public int getDate() {
        return date;
    }

    public SongComment setDate(int date) {
        this.date = date;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public SongComment setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getCommentId() {
        return commentId;
    }

    public SongComment setCommentId(int commentId) {
        this.commentId = commentId;
        return this;
    }
}
