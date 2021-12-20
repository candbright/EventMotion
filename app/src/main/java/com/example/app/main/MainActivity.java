package com.example.app.main;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.app.R;
import com.example.app.base.activity.BaseToolActivity;
import com.example.app.base.adapter.SortedItem;
import com.example.app.common.adapter.MyDiffAdapter;
import com.example.app.common.bean.Song;
import com.example.app.common.item.ImageCardItem;
import com.example.app.common.listener.OnItemEventListener;
import com.example.app.dao.SongDaoHelper;
import com.example.app.databinding.ActivityMainBinding;
import com.example.app.databinding.NavigationBarBinding;
import com.example.app.databinding.NavigationBottomBarBinding;
import com.example.app.databinding.SelectorBarBinding;
import com.example.app.global.GlobalApp;

import java.util.ArrayList;
import java.util.List;

import static com.example.app.common.bean.Song.DOUBLE;
import static com.example.app.common.bean.Song.INSANE;
import static com.example.app.common.bean.Song.LOVER;
import static com.example.app.common.bean.Song.MODE_FANCY;
import static com.example.app.common.bean.Song.MODE_RACE;
import static com.example.app.common.bean.Song.PERFORM;
import static com.example.app.common.bean.Song.REMIX;
import static com.example.app.common.bean.Song.STUDY;


/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainActivity extends BaseToolActivity<ActivityMainBinding> {

    private static final String TAG = "<MainActivity>";
    public static final String MODE_ALL = "MODE_ALL";
    public static final String MODE_DETAIL_ALL = "MODE_DETAIL_ALL";
    NavigationBarBinding navigationBarTop;
    SelectorBarBinding selectorBar;
    NavigationBottomBarBinding navigationBarBottom;
    MyDiffAdapter sortedAdapter;
    List<SortedItem> mData;
    String mode = MODE_ALL;
    String modeDetail = MODE_DETAIL_ALL;

    @Override
    protected void onCreateViewModule() {
        initBinding();
        initData();
        matchMode();
        matchModeDetail();
    }

    private void initBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
        selectorBar = SelectorBarBinding.bind(rootBinding.getRoot());
        navigationBarBottom = NavigationBottomBarBinding.bind(rootBinding.getRoot());
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
        rootBinding.rvDataList.setAdapter(sortedAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    protected void newExternalRelations() {
        new MainRelations(this);
    }

    public void setOnItemEventListener(OnItemEventListener listener) {
        sortedAdapter.setOnItemListener(listener);
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
