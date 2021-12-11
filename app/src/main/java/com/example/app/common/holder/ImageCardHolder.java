package com.example.app.common.holder;

import android.view.View;

import com.example.app.base.adapter.BaseViewHolder;
import com.example.app.common.item.ImageCardItem;
import com.example.app.databinding.ItemCardImageBinding;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class ImageCardHolder extends BaseViewHolder<ImageCardItem, ItemCardImageBinding> {
    public ImageCardHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(ImageCardItem data) {
        viewBinding.itemCardImage.setImageResource(data.getImageSource());
    }
}
