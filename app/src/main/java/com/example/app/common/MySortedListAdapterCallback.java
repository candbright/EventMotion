package com.example.app.common;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedListAdapterCallback;

import com.example.app.item.SortedItem;

/**
 * author : wyh
 */
public class MySortedListAdapterCallback extends SortedListAdapterCallback<SortedItem> {

    public MySortedListAdapterCallback(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    public int compare(SortedItem o1, SortedItem o2) {
        return o1.getIndex() - o2.getIndex();
    }

    @Override
    public boolean areContentsTheSame(SortedItem oldItem, SortedItem newItem) {
        return oldItem.toString().equals(newItem.toString());
    }

    @Override
    public boolean areItemsTheSame(SortedItem item1, SortedItem item2) {
        return item1.getIndex() == item2.getIndex();
    }
}
