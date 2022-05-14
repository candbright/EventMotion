package com.example.app.base.adapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SortedItemList<ItemType extends SortedItem> {

    private boolean isCustomMode;
    private List<ItemType> datas;

    public SortedItemList() {
        datas = new ArrayList<>();
    }

    public SortedItemList(boolean isCustomMode) {
        datas = new ArrayList<>();
        this.isCustomMode = isCustomMode;
    }

    public SortedItemList(List<ItemType> datas) {
        this.datas = new ArrayList<>(datas);
    }

    public SortedItemList(List<ItemType> datas, boolean isCustomMode) {
        this.datas = new ArrayList<>(datas);
        this.isCustomMode = isCustomMode;
    }

    public List list() {
        return datas;
    }

    public int size() {
        return datas.size();
    }

    public boolean isEmpty() {
        return datas.isEmpty();
    }


    public boolean add(ItemType o) {
        if (isCustomMode) {
            return datas.add(o);
        }
        return datas.add((ItemType) o.setSortedIndex(datas.size()));
    }

    public boolean remove(@Nullable ItemType o) {
        if (isCustomMode) {
            return datas.remove(o);
        }
        boolean isSucceed = datas.remove(o);
        if (isSucceed) {
            int sortedIndex = o.getSortedIndex();
            for (int i = sortedIndex + 1; i < datas.size() + 1; i++) {
                datas.get(i).setSortedIndex(i - 1);
            }
        }
        return isSucceed;
    }

    public ItemType get(int index) {
        return datas.get(index);
    }


    public void add(int index, ItemType element) {
        if (isCustomMode) {
            datas.add(index, element);
            return;
        }
        datas.add(index, (ItemType) element.setSortedIndex(index));
    }

    public ItemType remove(int index) {
        if (isCustomMode) {
            return datas.remove(index);
        }
        SortedItem removedItem = datas.remove(index);
        for (int i = index + 1; i < datas.size() + 1; i++) {
            datas.get(i).setSortedIndex(i - 1);
        }
        return (ItemType) removedItem;
    }

    public void clear() {
        datas.clear();
    }

}
