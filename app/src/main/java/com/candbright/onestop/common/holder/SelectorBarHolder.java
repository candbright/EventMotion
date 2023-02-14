package com.candbright.onestop.common.holder;

import android.view.View;

import com.candbright.onestop.R;
import com.candbright.onestop.base.adapter.BaseViewHolder;
import com.candbright.onestop.common.item.SelectorBarItem;
import com.candbright.onestop.databinding.ItemBarSelectorBinding;
import com.candbright.onestop.util.Utility;

/**
 * <p>created by wyh in 2021/12/11</p>
 */
public class SelectorBarHolder extends BaseViewHolder<SelectorBarItem, ItemBarSelectorBinding> {
    private static final String TAG = SelectorBarHolder.class.getSimpleName();

    public SelectorBarHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(SelectorBarItem data) {
        rootBinding.tvMode.setText(data.getText());
        if (!data.isSelected()) {
            rootBinding.tvMode.setTextColor(Utility.getColor(R.color.color_text_hint));
        } else {
            rootBinding.tvMode.setTextColor(Utility.getColor(R.color.pantone_flesh_2));
        }
        rootBinding.tvMode.setOnClickListener(v -> {
            mListener.onItemEvent(data.getSortedIndex(), null);
        });
    }
}
