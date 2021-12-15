package com.example.app.manager;

import android.view.View;

import com.example.app.databinding.NavigationBarBinding;

/**
 * <p>created by wyh in 2021/12/15</p>
 */
public class NavigationBarManager {
    public static final String MODE_NAV_TOP_NORMAL = "MODE_NAV_TOP_NORMAL";
    public static final String MODE_NAV_TOP_NO_LEFT_IMAGE = "MODE_NAV_TOP_NO_LEFT_IMAGE";
    public static final String MODE_NAV_TOP_NO_RIGHT_IMAGE = "MODE_NAV_TOP_NO_RIGHT_IMAGE";
    private NavigationBarBinding navigationBar;

    public NavigationBarManager(NavigationBarBinding navigationBar) {
        this(navigationBar, MODE_NAV_TOP_NORMAL);
    }

    public NavigationBarManager(NavigationBarBinding navigationBar, String mode) {
        this.navigationBar = navigationBar;
        switch (mode) {
            case MODE_NAV_TOP_NO_LEFT_IMAGE:
                navigationBar.menuButton.setVisibility(View.GONE);
                break;
            case MODE_NAV_TOP_NO_RIGHT_IMAGE:
                navigationBar.searchButton.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public NavigationBarManager setLeftOnClickListener(View.OnClickListener listener) {
        navigationBar.menuButton.setOnClickListener(listener);
        return this;
    }

    public NavigationBarManager setRightOnClickListener(View.OnClickListener listener) {
        navigationBar.searchButton.setOnClickListener(listener);
        return this;
    }

    public NavigationBarManager setTitle(String title) {
        navigationBar.titleTv.setText(title);
        return this;
    }

    public NavigationBarManager setTitle(int stringRes) {
        navigationBar.titleTv.setText(stringRes);
        return this;
    }
}
