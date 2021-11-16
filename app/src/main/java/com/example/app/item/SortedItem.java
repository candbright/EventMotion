package com.example.app.item;

import com.example.app.common.Viewable;

/**
 * author : yuhao wang
 * date   : 2021/10/19 10:12
 */
public abstract class SortedItem implements Viewable {
    private int index;

    public int getIndex() {
        return index;
    }

    public SortedItem setIndex(int index) {
        this.index = index;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }

}
