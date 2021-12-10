package com.example.app.base.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : yuhao wang
 * date   : 2021/10/19 10:12
 */
public abstract class SortedItem implements Cloneable {
    private int sortedIndex;

    public abstract int layoutID();

    public abstract RecyclerView.ViewHolder obtainViewHolder(ViewGroup parent, boolean attachToRoot);

    public int getSortedIndex() {
        return sortedIndex;
    }

    public SortedItem setSortedIndex(int index) {
        this.sortedIndex = index;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(sortedIndex);
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
