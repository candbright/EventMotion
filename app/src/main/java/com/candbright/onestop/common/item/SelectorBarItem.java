package com.candbright.onestop.common.item;

import androidx.annotation.NonNull;

import com.candbright.onestop.base.adapter.SortedItem;
import com.candbright.onestop.common.holder.SelectorBarHolder;
import com.candbright.onestop.databinding.ItemBarSelectorBinding;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class SelectorBarItem extends SortedItem<SelectorBarHolder, ItemBarSelectorBinding> {
    //item内容
    private String text;
    //是否选中
    private boolean isSelected = false;

    public String getText() {
        return text;
    }

    public SelectorBarItem setText(String text) {
        this.text = text;
        return this;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public SelectorBarItem setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

    @Override
    protected SelectorBarHolder createViewHolder(ItemBarSelectorBinding viewBinding) {
        return new SelectorBarHolder(viewBinding.getRoot());
    }

    @NonNull
    @Override
    public SelectorBarItem clone() {
        return (SelectorBarItem) super.clone();
    }
}
