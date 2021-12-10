package com.example.app.common.listener;

import android.os.Bundle;

public interface OnItemEventListener {
    default void onItemEvent(int sortedIndex, Bundle data) {
        onItemEvent(sortedIndex, false, data);
    }

    void onItemEvent(int sortedIndex, boolean switchValue, Bundle data);
}