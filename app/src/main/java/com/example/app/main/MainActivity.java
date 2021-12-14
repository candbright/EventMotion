package com.example.app.main;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.app.R;
import com.example.app.base.activity.BaseActivity;
import com.example.app.base.adapter.SortedItem;
import com.example.app.common.adapter.MyDiffAdapter;
import com.example.app.common.adapter.MyItemDecor;
import com.example.app.common.bean.Song;
import com.example.app.common.item.ImageCardItem;
import com.example.app.common.listener.OnItemEventListener;
import com.example.app.dao.SongDaoHelper;
import com.example.app.databinding.ActivityMainBinding;
import com.example.app.databinding.NavigationBarBinding;
import com.example.app.databinding.NavigationBottomBarBinding;
import com.example.app.global.GlobalApp;
import com.example.app.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final String TAG = "<MainActivity>";
    public static final int INDEX_GO_ACTIVITY_REGISTER = 0;
    public static final int INDEX_LIGHT_MODE_NIGHT = 1;
    public static final int INDEX_LIGHT_MODE_DAY = 2;

    private NavigationBarBinding navigationBarTop;
    private NavigationBottomBarBinding navigationBarBottom;
    private MyDiffAdapter sortedAdapter;
    private List<SortedItem> mData;
    private int navigatorId;

    @Override
    protected void onCreateViewModule() {
        initNavigatorTop();
        initNavigatorBottom();
        initData();
    }


    private void initNavigatorTop() {
        navigationBarTop = NavigationBarBinding.bind(viewBinding.getRoot());
        navigationBarTop.titleTv.setText(R.string.navigation_title_choose_mode);
    }

    private void initNavigatorBottom() {
        navigationBarBottom = NavigationBottomBarBinding.bind(viewBinding.getRoot());
        navigationBarBottom.musicImage.setImageResource(R.drawable.navigation_star_songs);
        navigationBarBottom.musicTv.setText(R.string.navigation_bottom_songs);
        navigationBarBottom.squareImage.setImageResource(R.drawable.navigation_star_square);
        navigationBarBottom.squareTv.setText(R.string.navigation_bottom_square);
        navigationBarBottom.teachImage.setImageResource(R.drawable.navigation_star_teach);
        navigationBarBottom.teachTv.setText(R.string.navigation_bottom_teach);
        navigationBarBottom.collectImage.setImageResource(R.drawable.navigation_star_collect);
        navigationBarBottom.collectTv.setText(R.string.navigation_bottom_collect);
        navigationBarBottom.goCameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        matchBottomTv();
    }

    private void initData() {
        mData = new ArrayList<>();
        SongDaoHelper songDaoHelper = SongDaoHelper.getInstance(GlobalApp.getInstance());
        List<Song> songs = songDaoHelper.searchAll();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            Log.d(TAG, "song[" + i + "].id = " + song.getId());
            mData.add(new ImageCardItem().setImageSource(song.getImageSrc())
                    .setSongName(song.getSongName())
                    .setDifficulty(song.getDifficulty())
                    .setDescription(song.getDescription()));
        }
        sortedAdapter = new MyDiffAdapter(mData);
        viewBinding.rvDataList.setAdapter(sortedAdapter);
        viewBinding.rvDataList.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.rvDataList.addItemDecoration(new MyItemDecor(MyItemDecor.TYPE_VERTICAL,
                (int) Utility.dip2px(10),
                (int) Utility.dip2px(10),
                (int) Utility.dip2px(10)));
    }

    @Override
    protected void newExternalRelations() {
        new MainRelations(this);
    }

    public void setOnItemEventListener(OnItemEventListener listener) {
        sortedAdapter.setOnItemListener(listener);
    }

    public void setMusicButtonClickListener(View.OnClickListener listener) {
        navigationBarBottom.musicButton.setOnClickListener(v -> {
            navigatorId = 0;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setSquareButtonClickListener(View.OnClickListener listener) {
        navigationBarBottom.squareButton.setOnClickListener(v -> {
            navigatorId = 1;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setTeachButtonClickListener(View.OnClickListener listener) {
        navigationBarBottom.teachButton.setOnClickListener(v -> {
            navigatorId = 2;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setCollectButtonClickListener(View.OnClickListener listener) {
        navigationBarBottom.collectButton.setOnClickListener(v -> {
            navigatorId = 3;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void matchBottomTv() {
        navigationBarBottom.musicTv.setVisibility(View.GONE);
        navigationBarBottom.teachTv.setVisibility(View.GONE);
        navigationBarBottom.collectTv.setVisibility(View.GONE);
        navigationBarBottom.squareTv.setVisibility(View.GONE);
        switch (navigatorId) {
            case 0:
                navigationBarBottom.musicTv.setVisibility(View.VISIBLE);
                break;
            case 1:
                navigationBarBottom.squareTv.setVisibility(View.VISIBLE);
                break;
            case 2:
                navigationBarBottom.teachTv.setVisibility(View.VISIBLE);
                break;
            case 3:
                navigationBarBottom.collectTv.setVisibility(View.VISIBLE);
                break;
        }
    }
}
