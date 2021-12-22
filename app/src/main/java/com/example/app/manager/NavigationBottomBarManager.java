package com.example.app.manager;

import android.view.View;

import com.example.app.R;
import com.example.app.databinding.NavigationBottomBarBinding;

/**
 * <p>created by wyh in 2021/12/15</p>
 */
public class NavigationBottomBarManager {
    private NavigationBottomBarBinding navigationBottomBar;
    int navigatorId;

    public NavigationBottomBarManager(NavigationBottomBarBinding navigationBottomBar) {
        this.navigationBottomBar = navigationBottomBar;
        init();
        matchBottomTv();
    }

    public void init() {
        navigationBottomBar.musicImage.setImageResource(R.drawable.navigation_star_songs);
        navigationBottomBar.musicTv.setText(R.string.navigation_bottom_songs);
        navigationBottomBar.officialImage.setImageResource(R.drawable.navigation_star_square);
        navigationBottomBar.officialTv.setText(R.string.navigation_bottom_songs_official);
        navigationBottomBar.midImage.setImageResource(R.drawable.navigation_star_ufo);
        navigationBottomBar.midTv.setText(R.string.navigation_bottom_square);
        navigationBottomBar.teachImage.setImageResource(R.drawable.navigation_star_teach);
        navigationBottomBar.teachTv.setText(R.string.navigation_bottom_teach);
        navigationBottomBar.myImage.setImageResource(R.drawable.navigation_star_collect);
        navigationBottomBar.myTv.setText(R.string.navigation_bottom_my);

        navigationBottomBar.midButton.setOnClickListener(v -> {

        });
    }

    public void matchBottomTv() {
        navigationBottomBar.musicTv.setVisibility(View.GONE);
        navigationBottomBar.teachTv.setVisibility(View.GONE);
        navigationBottomBar.myTv.setVisibility(View.GONE);
        navigationBottomBar.midTv.setVisibility(View.GONE);
        navigationBottomBar.officialTv.setVisibility(View.GONE);
        switch (navigatorId) {
            case 0:
                navigationBottomBar.musicTv.setVisibility(View.VISIBLE);
                break;
            case 1:
                navigationBottomBar.officialTv.setVisibility(View.VISIBLE);
                break;
            case 2:
                navigationBottomBar.midTv.setVisibility(View.VISIBLE);
                break;
            case 3:
                navigationBottomBar.teachTv.setVisibility(View.VISIBLE);
                break;
            case 4:
                navigationBottomBar.myTv.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setMusicButtonClickListener(View.OnClickListener listener) {
        navigationBottomBar.musicButton.setOnClickListener(v -> {
            navigatorId = 0;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setOfficialButtonClickListener(View.OnClickListener listener) {
        navigationBottomBar.officialButton.setOnClickListener(v -> {
            navigatorId = 1;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setMidButtonClickListener(View.OnClickListener listener) {
        navigationBottomBar.midButton.setOnClickListener(v -> {
            navigatorId = 2;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setTeachButtonClickListener(View.OnClickListener listener) {
        navigationBottomBar.teachButton.setOnClickListener(v -> {
            navigatorId = 3;
            matchBottomTv();
            listener.onClick(v);
        });

    }

    public void setCollectButtonClickListener(View.OnClickListener listener) {
        navigationBottomBar.myButton.setOnClickListener(v -> {
            navigatorId = 4;
            matchBottomTv();
            listener.onClick(v);
        });
    }
}
