package com.example.app.main;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * <p>created by wyh in 2021/12/10</p>
 */
public class MainGridManager extends GridLayoutManager {
    public MainGridManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MainGridManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MainGridManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public int getSpanCount() {
        return 2;
    }
}
