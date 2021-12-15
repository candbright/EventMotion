package com.example.app.common.listener;

import android.os.Bundle;

public interface OnItemEventListener {
    default void onItemEvent(long sortedIndex, Bundle data) {
        onItemEvent(sortedIndex, false, data);
    }

    void onItemEvent(long sortedIndex, boolean switchValue, Bundle data);
}