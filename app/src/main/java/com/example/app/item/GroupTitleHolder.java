package com.example.app.item;

import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.common.ItemViewHolder;

/**
 * Created by NiXing on 2018/11/27.
 */
public class GroupTitleHolder extends ItemViewHolder<GroupTitleItem> {
    private TextView mTvGroupTitle;
    private TextView mTvRefresh;
    private ImageView mImRefresh;

    public GroupTitleHolder(View itemView) {
        super(itemView);
        mTvGroupTitle = itemView.findViewById(R.id.tv_group_title);
        mTvRefresh = itemView.findViewById(R.id.tv_refresh);
        mImRefresh = itemView.findViewById(R.id.im_refresh);

    }

    @Override
    public void bindViewData(final GroupTitleItem data) {
        if (data.getGravity() != Gravity.BOTTOM) {
            mTvGroupTitle.setGravity(data.getGravity());
        }
        mTvGroupTitle.setText(data.getText());
        mTvRefresh.setVisibility(data.isRefresh() ? View.VISIBLE : View.INVISIBLE);
        mImRefresh.setVisibility(data.isRefresh() ? View.VISIBLE : View.INVISIBLE);
        if (mListener != null && data.isRefresh()) {
            itemView.setOnClickListener(v -> mListener.onItemEvent(data.getText(), true, null));
        }
    }
}
