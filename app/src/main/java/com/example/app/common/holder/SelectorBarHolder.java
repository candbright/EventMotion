package com.example.app.common.holder;

import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.app.R;
import com.example.app.base.adapter.BaseViewHolder;
import com.example.app.common.item.SelectorBarItem;
import com.example.app.databinding.ItemBarSelectorBinding;
import com.example.app.global.GlobalApp;
import com.example.app.util.Utility;

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
