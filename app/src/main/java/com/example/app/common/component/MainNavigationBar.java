package com.example.app.common.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.app.R;
import com.example.app.base.view.BaseConstraintLayout;

/**
 * <p>created by wyh in 2021/12/10</p>
 */
public class MainNavigationBar extends BaseConstraintLayout {

    private ImageView mIvLeft;
    private ImageView mIvRight;
    private TextView mTvTitle;

    public MainNavigationBar(Context context) {
        this(context, null);
    }

    public MainNavigationBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainNavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MainNavigationBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    public int layoutId() {
        return R.layout.component_navigation_bar_main;
    }

    @Override
    protected void findView() {
        mIvLeft = mInflateLayout.findViewById(R.id.iv_component_navigation_bar_menu);
        mIvRight = mInflateLayout.findViewById(R.id.iv_component_navigation_bar_search);
        mTvTitle = mInflateLayout.findViewById(R.id.tv_component_navigation_bar_title);
    }

    private void initView() {
        //
    }
}
