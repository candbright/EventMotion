package com.example.app.item;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.app.R;
import com.example.app.common.ItemViewHolder;

/**
 * created by wyh in 2021/10/26
 */

public class SimpleCardHolder extends ItemViewHolder<SimpleCardItem> {
    private TextView mTvExplain;
    private CardView mMainView;

    public SimpleCardHolder(View itemView) {
        super(itemView);
        mTvExplain = itemView.findViewById(R.id.tv_item_card_explain);
        mMainView = itemView.findViewById(R.id.cv_item_card_main);
    }

    @Override
    public void bindViewData(final SimpleCardItem data) {
        if (data.getExplainText() != null) {
            mTvExplain.setText(data.getExplainText());
        }
        mMainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    Bundle extraData = new Bundle();
                    mListener.onItemEvent(String.valueOf(data.getIndex()), true, extraData);
                }
            }
        });
    }
}

