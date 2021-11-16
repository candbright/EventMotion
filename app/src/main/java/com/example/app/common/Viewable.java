package com.example.app.common;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * author : wyh
 */

public interface Viewable {
    int layoutID();
    RecyclerView.ViewHolder obtainViewHolder(ViewGroup parent, boolean attachToRoot);
}
