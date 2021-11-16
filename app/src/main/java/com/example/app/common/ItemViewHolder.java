package com.example.app.common;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by NiXing on 2018/11/19.
 */
public abstract class ItemViewHolder<T> extends RecyclerView.ViewHolder {

    protected OnItemEventListener mListener;

    public ItemViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewData(T data);

    public void setOnItemEventListener(OnItemEventListener l) {
        mListener = l;
    }
}
