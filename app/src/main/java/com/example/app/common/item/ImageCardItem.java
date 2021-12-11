package com.example.app.common.item;

import com.example.app.base.adapter.SortedItem;
import com.example.app.common.holder.ImageCardHolder;
import com.example.app.databinding.ItemCardImageBinding;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class ImageCardItem extends SortedItem<ImageCardHolder, ItemCardImageBinding> {
    @Override
    protected ImageCardHolder createViewHolder(ItemCardImageBinding viewBinding) {
        return new ImageCardHolder(viewBinding.getRoot());
    }
}
