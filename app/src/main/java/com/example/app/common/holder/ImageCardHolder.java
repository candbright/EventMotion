package com.example.app.common.holder;

import android.util.Log;
import android.view.View;

import com.example.app.R;
import com.example.app.base.adapter.BaseViewHolder;
import com.example.app.common.item.ImageCardItem;
import com.example.app.databinding.ItemCardImageBinding;
import com.example.app.global.GlobalApp;
import com.example.app.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class ImageCardHolder extends BaseViewHolder<ImageCardItem, ItemCardImageBinding> {
    private static final String TAG = ImageCardHolder.class.getSimpleName();

    public ImageCardHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(ImageCardItem data) {
        rootBinding.songImage.setImageResource(Utility.getResId(data.getImageSource()));
        rootBinding.songName.setText(String.format(GlobalApp.getResString(R.string.item_card_song_name), data.getSongName()));
        rootBinding.songDifficulty.setText(String.format(GlobalApp.getResString(R.string.item_card_song_difficulty), data.getDifficulty()));
        rootBinding.songDescription.setText(String.format(GlobalApp.getResString(R.string.item_card_song_description), data.getDescription()));
        rootBinding.cardRoot.setOnClickListener(v -> {
            Log.d(TAG, "You have clicked the song list,the current song: " + data.getSongName() + "[id = " + data.getSortedIndex() + "].");
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
