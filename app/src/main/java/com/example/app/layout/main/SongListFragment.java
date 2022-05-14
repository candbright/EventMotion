package com.example.app.layout.main;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.app.R;
import com.example.app.base.adapter.SortedItem;
import com.example.app.base.adapter.SortedItemList;
import com.example.app.base.fragment.BaseFragment;
import com.example.app.base.fragment.FragmentLifecycleListener;
import com.example.app.common.adapter.MyDiffAdapter;
import com.example.app.common.bean.Song;
import com.example.app.common.item.ImageCardItem;
import com.example.app.common.item.SelectorBarItem;
import com.example.app.dao.DaoMaster;
import com.example.app.dao.DaoSession;
import com.example.app.dao.SongDao;
import com.example.app.dao.SongDaoHelper;
import com.example.app.databinding.FragmentSongListBinding;
import com.example.app.databinding.NavigationBarBinding;
import com.example.app.global.GlobalApp;
import com.example.app.layout.web.WebActivity;
import com.example.app.manager.NavigationBarManager;

import java.util.List;

import static com.example.app.layout.web.WebActivity.WEB_URL_KEY;

/**
 * <p>created by wyh in 2021/12/21</p>
 */
public class SongListFragment extends BaseFragment<FragmentSongListBinding> {
    private static final String TAG = SongListFragment.class.getSimpleName();
    private NavigationBarBinding navigationBarTop;
    private NavigationBarManager navigationBarTopManager;
    private SongDaoHelper songDaoHelper;

    MyDiffAdapter sortedAdapter;
    SortedItemList<ImageCardItem> mData;

    MyDiffAdapter modeAdapter;
    SortedItemList<SelectorBarItem> mModeData;
    int oldModeIndex;
    String mode;

    MyDiffAdapter modeDetailAdapter;
    SortedItemList<SelectorBarItem> mModeDetailData;
    int oldModeDetailIndex;
    String modeDetail;

    @Override
    protected void initViewBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
    }

    @Override
    protected FragmentLifecycleListener createLifecycleListener() {
        return new FragmentLifecycleListener() {
            @Override
            public void onViewCreated() {
                super.onViewCreated();
                initData();
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
    }

    private void initData() {

        //模式
        mode = getString(R.string.all_song);
        mModeData = getModeData();
        modeAdapter = new MyDiffAdapter(mModeData.list());
        rootBinding.rvModeList.setAdapter(modeAdapter);
        rootBinding.rvModeList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        modeAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
            if (sortedIndex == oldModeIndex) {
                return;
            }
            SelectorBarItem oldItem = (SelectorBarItem) modeAdapter.getChangedItem(oldModeIndex);
            oldItem.setSelected(false);
            SelectorBarItem changedItem = (SelectorBarItem) modeAdapter.getChangedItem((int) sortedIndex);
            changedItem.setSelected(true);
            mode = changedItem.getText();
            oldModeIndex = changedItem.getSortedIndex();
            modeAdapter.notifyDiff();
            //刷新界面数据
            setData(songDaoHelper.searchByModeAndDetail(mode, modeDetail));
        });
        //具体模式
        modeDetail = getString(R.string.all_song);
        mModeDetailData = getMModeDetailData();
        modeDetailAdapter = new MyDiffAdapter(mModeDetailData.list());
        rootBinding.rvModeDetailList.setAdapter(modeDetailAdapter);
        rootBinding.rvModeDetailList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        modeDetailAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
            if (sortedIndex == oldModeDetailIndex) {
                return;
            }
            SelectorBarItem oldItem = (SelectorBarItem) modeDetailAdapter.getChangedItem(oldModeDetailIndex);
            oldItem.setSelected(false);
            SelectorBarItem changedItem = (SelectorBarItem) modeDetailAdapter.getChangedItem((int) sortedIndex);
            changedItem.setSelected(true);
            modeDetail = changedItem.getText();
            oldModeDetailIndex = changedItem.getSortedIndex();
            modeDetailAdapter.notifyDiff();
            //刷新界面数据
            setData(songDaoHelper.searchByModeAndDetail(mode, modeDetail));
        });
        //主界面数据
        mData = new SortedItemList(true);
        SongDaoHelper songDaoHelper = SongDaoHelper.getInstance(GlobalApp.getInstance());
        List<Song> songs = songDaoHelper.searchAll();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            mData.add((ImageCardItem) new ImageCardItem().setImageSource(song.getImageSrc())
                    .setSongName(song.getSongName())
                    .setDifficulty(song.getDifficulty())
                    .setDescription(song.getDescription())
                    .setSortedIndex(song.getId().intValue()));
        }
        sortedAdapter = new MyDiffAdapter(mData.list());
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
            mData.add((ImageCardItem) new ImageCardItem().setImageSource(song.getImageSrc())
                    .setSongName(song.getSongName())
                    .setDifficulty(song.getDifficulty())
                    .setDescription(song.getDescription())
                    .setSortedIndex(song.getId().intValue()));
        }
        sortedAdapter.notifyDiff();
    }

    public SortedItemList getModeData() {
        SortedItemList datas = new SortedItemList();
        datas.add(new SelectorBarItem().setText(getString(R.string.all_song)).setSelected(true));
        datas.add(new SelectorBarItem().setText(getString(R.string.mode_fancy)));
        datas.add(new SelectorBarItem().setText(getString(R.string.mode_speed)));
        return datas;
    }

    public SortedItemList getMModeDetailData() {
        SortedItemList datas = new SortedItemList();
        datas.add(new SelectorBarItem().setText(getString(R.string.all_song)).setSelected(true));
        datas.add(new SelectorBarItem().setText(getString(R.string.mode_study)));
        datas.add(new SelectorBarItem().setText(getString(R.string.mode_perform)));
        datas.add(new SelectorBarItem().setText(getString(R.string.mode_insane)));
        datas.add(new SelectorBarItem().setText(getString(R.string.mode_double)));
        datas.add(new SelectorBarItem().setText(getString(R.string.mode_lover)));
        datas.add(new SelectorBarItem().setText(getString(R.string.mode_remix)));
        return datas;
    }

}
