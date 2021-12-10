package com.example.app.base.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app.common.listener.OnItemEventListener;

/**
 * Created by NiXing on 2018/11/19.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected OnItemEventListener mListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewData(T data);

    public void setOnItemEventListener(OnItemEventListener l) {
        mListener = l;
    }
}
