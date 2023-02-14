package com.candbright.onestop.common.item;

import androidx.annotation.NonNull;

import com.candbright.onestop.base.adapter.SortedItem;
import com.candbright.onestop.common.holder.ImageCardHolder;
import com.candbright.onestop.databinding.ItemCardImageBinding;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class ImageCardItem extends SortedItem<ImageCardHolder, ItemCardImageBinding> {
    //歌曲封面路径
    private String imageSource;
    //歌曲名称
    private String songName;
    //歌曲难度
    private int difficulty;
    //歌曲描述
    private String description;

    public String getImageSource() {
        return imageSource;
    }

    public ImageCardItem setImageSource(String imageSource) {
        this.imageSource = imageSource;
        return this;
    }

    public String getSongName() {
        return songName;
    }

    public ImageCardItem setSongName(String songName) {
        this.songName = songName;
        return this;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public ImageCardItem setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ImageCardItem setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    protected ImageCardHolder createViewHolder(ItemCardImageBinding viewBinding) {
        return new ImageCardHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public ImageCardItem clone() {
        return (ImageCardItem) super.clone();
    }
}
