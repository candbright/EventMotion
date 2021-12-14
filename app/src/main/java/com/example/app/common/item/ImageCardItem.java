package com.example.app.common.item;

import com.example.app.base.adapter.SortedItem;
import com.example.app.common.holder.ImageCardHolder;
import com.example.app.databinding.ItemCardImageBinding;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class ImageCardItem extends SortedItem<ImageCardHolder, ItemCardImageBinding> {
    //歌曲封面路径
    private int imageSource;
    //歌曲名称
    private String songName;
    //歌曲难度
    private int difficulty;
    //歌曲描述
    private String description;
    public int getImageSource() {
        return imageSource;
    }

    public ImageCardItem setImageSource(int imageSource) {
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


}
