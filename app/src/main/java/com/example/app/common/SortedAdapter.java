package com.example.app.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.app.item.SortedItem;

/**
 * author : wyh
 */
public class SortedAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    SortedList<SortedItem> mData;
    private OnItemEventListener mListener;

    public SortedAdapter() {
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SortedItem sortedItem = null;
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).layoutID() == viewType) {
                sortedItem = mData.get(i);
                break;
            }
        }

        if (null == sortedItem) {
            return null;
        }

        return (ItemViewHolder) sortedItem.obtainViewHolder(parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (mListener != null) {
            holder.setOnItemEventListener(mListener);
        }
        holder.bindViewData(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (mData == null || position < 0 || position >= mData.size()) {
            return -1;
        }
        return mData.get(position).layoutID();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemEventListener(OnItemEventListener l) {
        mListener = l;
    }

    public void setSortedList(SortedList<SortedItem> mSortedList) {
        this.mData = mSortedList;
    }

    public void removeItem(int index) {
        mData.beginBatchedUpdates();
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getIndex() == index) {
                mData.removeItemAt(i);
            }
        }
        mData.endBatchedUpdates();
    }

    public void addItem(SortedItem item) {
        mData.beginBatchedUpdates();
        mData.add(item);
        mData.endBatchedUpdates();
    }
}
