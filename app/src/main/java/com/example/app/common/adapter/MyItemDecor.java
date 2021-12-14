package com.example.app.common.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>created by wyh in 2021/12/14</p>
 */
public class MyItemDecor extends RecyclerView.ItemDecoration {

    private static final String TAG = "BCItemDecor";
    private final int space;
    private final int type;
    private final int firstItemMargin;
    private final int lastItemMargin;
    public static final int TYPE_HORIZONTAL = 0;
    public static final int TYPE_VERTICAL = 1;
    public static final int TYPE_GRID = 2;

    public MyItemDecor(int type, int itemMargin) {
        this.type = type;
        this.space = itemMargin;
        this.firstItemMargin = 0;
        this.lastItemMargin = 0;
    }

    public MyItemDecor(int type, int itemMargin, int firstItemMargin, int lastItemMargin) {
        this.type = type;
        this.space = itemMargin;
        this.firstItemMargin = firstItemMargin;
        this.lastItemMargin = lastItemMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int current = parent.getChildLayoutPosition(view);
        boolean isFirstItem = current == 0;
        boolean isLastItem = current == parent.getAdapter().getItemCount() - 1;
        switch (type) {
            case TYPE_VERTICAL:
                outRect.top = isFirstItem ? firstItemMargin : 0;
                outRect.bottom = isLastItem ? lastItemMargin : space;
                outRect.left = 0;
                outRect.right = 0;
                break;
            case TYPE_HORIZONTAL:
                outRect.top = 0;
                outRect.bottom = 0;
                outRect.left = isFirstItem ? firstItemMargin : 0;
                outRect.right = isLastItem ? lastItemMargin : space;
                break;
            case TYPE_GRID:
                outRect.top = space;
                outRect.bottom = space;
                outRect.left = space;
                outRect.right = space;
                break;
        }
    }
}
