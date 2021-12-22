package com.example.app.main;

import static com.example.app.common.bean.Song.DOUBLE;
import static com.example.app.common.bean.Song.INSANE;
import static com.example.app.common.bean.Song.LOVER;
import static com.example.app.common.bean.Song.MODE_FANCY;
import static com.example.app.common.bean.Song.MODE_RACE;
import static com.example.app.common.bean.Song.PERFORM;
import static com.example.app.common.bean.Song.REMIX;
import static com.example.app.common.bean.Song.STUDY;
import static com.example.app.web.WebActivity.WEB_URL_KEY;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.app.R;
import com.example.app.base.adapter.SortedItem;
import com.example.app.base.fragment.BaseFragment;
import com.example.app.base.fragment.FragmentLifecycleListener;
import com.example.app.common.adapter.MyDiffAdapter;
import com.example.app.common.bean.Song;
import com.example.app.common.item.ImageCardItem;
import com.example.app.dao.SongDaoHelper;
import com.example.app.databinding.FragmentSongListBinding;
import com.example.app.databinding.NavigationBarBinding;
import com.example.app.databinding.SelectorBarBinding;
import com.example.app.global.GlobalApp;
import com.example.app.manager.NavigationBarManager;
import com.example.app.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>created by wyh in 2021/12/21</p>
 */
public class SongListFragment extends BaseFragment<FragmentSongListBinding> {
    private static final String TAG = SongListFragment.class.getSimpleName();
    public static final String MODE_ALL = "MODE_ALL";
    public static final String MODE_DETAIL_ALL = "MODE_DETAIL_ALL";
    private NavigationBarBinding navigationBarTop;
    private SelectorBarBinding selectorBar;
    private NavigationBarManager navigationBarTopManager;
    private SongDaoHelper songDaoHelper;

    MyDiffAdapter sortedAdapter;
    List<SortedItem> mData;
    String mode = MODE_ALL;
    String modeDetail = MODE_DETAIL_ALL;

    @Override
    protected void initViewBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
        selectorBar = SelectorBarBinding.bind(rootBinding.getRoot());
    }

    @Override
    protected FragmentLifecycleListener createLifecycleListener() {
        return new FragmentLifecycleListener() {
            @Override
            public void onViewCreated() {
                super.onViewCreated();
                initData();
                 matchMode();
                 matchModeDetail();
            }
        };
    }

    @Override
    protected void initManager() {
        songDaoHelper = SongDaoHelper.getInstance(getContext());
        navigationBarTopManager = new NavigationBarManager(navigationBarTop);
        navigationBarTopManager.setTitle(R.string.title_all_song);
        navigationBarTopManager.setLeftOnClickListener(v -> {

        });
        navigationBarTopManager.setRightOnClickListener(v -> {

        });

        getRootBinding().refreshLayout.setOnRefreshListener(() -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(mode, modeDetail);
            setData(songsSelect);
            getRootBinding().refreshLayout.setRefreshing(false);
        });
        selectorBar.allModeBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(MODE_ALL, modeDetail);
            setData(songsSelect);
            mode = MODE_ALL;
            matchMode();
        });
        selectorBar.fancyModeBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(MODE_FANCY, modeDetail);
            setData(songsSelect);
            mode = MODE_FANCY;
            matchMode();
        });
        selectorBar.raceModeBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(MODE_RACE, modeDetail);
            setData(songsSelect);
            mode = MODE_RACE;
            matchMode();
        });
        selectorBar.allDetailBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(mode, MODE_DETAIL_ALL);
            setData(songsSelect);
            modeDetail = MODE_DETAIL_ALL;
            matchModeDetail();
        });
        selectorBar.studyDetailBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(mode, STUDY);
            setData(songsSelect);
            modeDetail = STUDY;
            matchModeDetail();
        });
        selectorBar.performDetailBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(mode, PERFORM);
            setData(songsSelect);
            modeDetail = PERFORM;
            matchModeDetail();
        });
        selectorBar.insaneDetailBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(mode, INSANE);
            setData(songsSelect);
            modeDetail = INSANE;
            matchModeDetail();
        });
        selectorBar.doubleDetailBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(mode, DOUBLE);
            setData(songsSelect);
            modeDetail = DOUBLE;
            matchModeDetail();
        });
        selectorBar.loverDetailBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(mode, LOVER);
            setData(songsSelect);
            modeDetail = LOVER;
            matchModeDetail();
        });
        selectorBar.remixDetailBtn.setOnClickListener(v -> {
            List<Song> songsSelect = songDaoHelper.searchByModeAndDetail(mode, REMIX);
            setData(songsSelect);
            modeDetail = REMIX;
            matchModeDetail();
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        SongDaoHelper songDaoHelper = SongDaoHelper.getInstance(GlobalApp.getInstance());
        List<Song> songs = songDaoHelper.searchAll();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            mData.add(new ImageCardItem().setImageSource(song.getImageSrc())
                    .setSongName(song.getSongName())
                    .setDifficulty(song.getDifficulty())
                    .setDescription(song.getDescription())
                    .setSortedIndex(song.getId()));
        }
        sortedAdapter = new MyDiffAdapter(mData);
        rootBinding.rvDataList.setAdapter(sortedAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext()));
        sortedAdapter.setOnItemListener((tag, switchValue, data) -> {
            for (Song song : songs) {
                if (song.getId() == tag) {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(WEB_URL_KEY, song.getUrlPath().size() == 0 ? "" : song.getUrlPath().get(0));
                    startActivity(intent);
                }
            }
        });
    }

    public void setData(List<Song> data) {
        mData.clear();
        for (int i = 0; i < data.size(); i++) {
            Song song = data.get(i);
            Log.d(TAG, "add song[" + i + "]: id = " + song.getId());
            mData.add(new ImageCardItem().setImageSource(song.getImageSrc())
                    .setSongName(song.getSongName())
                    .setDifficulty(song.getDifficulty())
                    .setDescription(song.getDescription())
                    .setSortedIndex(song.getId()));
        }
        sortedAdapter.notifyDiff();
    }

    public void matchMode() {
        selectorBar.allModeBtn.setTextColor(getColor(R.color.color_text_hint));
        selectorBar.fancyModeBtn.setTextColor(getColor(R.color.color_text_hint));
        selectorBar.raceModeBtn.setTextColor(getColor(R.color.color_text_hint));
        switch (mode) {
            case MODE_ALL:
                selectorBar.allModeBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
            case MODE_FANCY:
                selectorBar.fancyModeBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
            case MODE_RACE:
                selectorBar.raceModeBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
        }
    }

    public void matchModeDetail() {
        selectorBar.allDetailBtn.setTextColor(getColor(R.color.color_text_hint));
        selectorBar.studyDetailBtn.setTextColor(getColor(R.color.color_text_hint));
        selectorBar.performDetailBtn.setTextColor(getColor(R.color.color_text_hint));
        selectorBar.insaneDetailBtn.setTextColor(getColor(R.color.color_text_hint));
        selectorBar.doubleDetailBtn.setTextColor(getColor(R.color.color_text_hint));
        selectorBar.loverDetailBtn.setTextColor(getColor(R.color.color_text_hint));
        selectorBar.remixDetailBtn.setTextColor(getColor(R.color.color_text_hint));
        switch (modeDetail) {
            case MODE_DETAIL_ALL:
                selectorBar.allDetailBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
            case STUDY:
                selectorBar.studyDetailBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
            case PERFORM:
                selectorBar.performDetailBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
            case INSANE:
                selectorBar.insaneDetailBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
            case DOUBLE:
                selectorBar.doubleDetailBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
            case LOVER:
                selectorBar.loverDetailBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
            case REMIX:
                selectorBar.remixDetailBtn.setTextColor(getColor(R.color.pantone_flesh_2));
                break;
        }
    }
}
