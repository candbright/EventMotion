package com.example.app.common.adapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedListAdapterCallback;

import com.example.app.base.adapter.SortedItem;

public class MySortedListAdapterCallback extends SortedListAdapterCallback<SortedItem> {

    public MySortedListAdapterCallback(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    public int compare(SortedItem o1, SortedItem o2) {
        return o1.getSortedIndex() - o2.getSortedIndex();
    }

    @Override
    public boolean areContentsTheSame(SortedItem oldItem, SortedItem newItem) {
        return oldItem.toString().equals(newItem.toString());
    }

    @Override
    public boolean areItemsTheSame(SortedItem item1, SortedItem item2) {
        return item1.getSortedIndex() == item2.getSortedIndex();
    }
}
