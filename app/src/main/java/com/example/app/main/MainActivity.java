package com.example.app.main;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.app.R;
import com.example.app.base.activity.BaseToolActivity;
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
public class MainActivity extends BaseToolActivity<ActivityMainBinding> {

    private static final String TAG = "<MainActivity>";

    NavigationBarBinding navigationBarTop;
    NavigationBottomBarBinding navigationBarBottom;
    private MyDiffAdapter sortedAdapter;
    private List<SortedItem> mData;
    int navigatorId;

    @Override
    protected void onCreateViewModule() {
        initBinding();
        initNavigatorBottom();
        initData();
    }

    private void initBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootView.getRoot());
        navigationBarBottom = NavigationBottomBarBinding.bind(rootView.getRoot());
    }

    private void initNavigatorBottom() {
        navigationBarBottom.musicImage.setImageResource(R.drawable.navigation_star_songs);
        navigationBarBottom.musicTv.setText(R.string.navigation_bottom_songs);
        navigationBarBottom.squareImage.setImageResource(R.drawable.navigation_star_square);
        navigationBarBottom.squareTv.setText(R.string.navigation_bottom_square);
        navigationBarBottom.teachImage.setImageResource(R.drawable.navigation_star_teach);
        navigationBarBottom.teachTv.setText(R.string.navigation_bottom_teach);
        navigationBarBottom.collectImage.setImageResource(R.drawable.navigation_star_collect);
        navigationBarBottom.collectTv.setText(R.string.navigation_bottom_collect);
        navigationBarBottom.goCameraView.setOnClickListener(v -> {

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
                    .setDescription(song.getDescription())
                    .setSortedIndex(song.getId()));
        }
        sortedAdapter = new MyDiffAdapter(mData);
        rootView.rvDataList.setAdapter(sortedAdapter);
        rootView.rvDataList.setLayoutManager(new LinearLayoutManager(this));
        rootView.rvDataList.addItemDecoration(new MyItemDecor(MyItemDecor.TYPE_VERTICAL,
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
