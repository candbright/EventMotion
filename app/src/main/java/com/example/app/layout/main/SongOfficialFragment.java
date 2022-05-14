package com.example.app.layout.main;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.app.R;
import com.example.app.base.adapter.SortedItem;
import com.example.app.base.fragment.BaseFragment;
import com.example.app.base.fragment.FragmentLifecycleListener;
import com.example.app.common.adapter.MyDiffAdapter;
import com.example.app.common.item.SelectorBarItem;
import com.example.app.databinding.FragmentSongOfficialBinding;
import com.example.app.databinding.NavigationBarBinding;
import com.example.app.manager.NavigationBarManager;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>created by wyh in 2021/12/22</p>
 */
public class SongOfficialFragment extends BaseFragment<FragmentSongOfficialBinding> {
    private NavigationBarBinding navigationBarTop;
    private NavigationBarManager navigationBarTopManager;

    MyDiffAdapter yearAdapter;
    List<SortedItem> mYearData;
    int oldYearIndex = 0;

    @Override
    protected void initViewBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
    }

    @Override
    protected void initManager() {
        navigationBarTopManager = new NavigationBarManager(navigationBarTop);
        navigationBarTopManager.setLeftImageSrc(0);
        navigationBarTopManager.setRightImageSrc(R.drawable.main_search);
        navigationBarTopManager.setTitle(R.string.title_official_song);
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

    private void initData() {
        mYearData = new ArrayList<>();
        mYearData.add(new SelectorBarItem().setText(getString(R.string.all_Year))
                .setSelected(true)
                .setSortedIndex(0));

        //TODO
        /*SongDaoHelper songDaoHelper = SongDaoHelper.getInstance(GlobalApp.getInstance());
        List<Song> songs = songDaoHelper.searchAll();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            mYearData.add(new SelectorBarItem().setText(song.getImageSrc())
                    .setSortedIndex(song.getId()));
        }*/
        mYearData.add(new SelectorBarItem().setText("2022")
                .setSortedIndex(1));
        mYearData.add(new SelectorBarItem().setText("2021")
                .setSortedIndex(2));
        mYearData.add(new SelectorBarItem().setText("2020")
                .setSortedIndex(3));
        yearAdapter = new MyDiffAdapter(mYearData);
        rootBinding.rvDataList.setAdapter(yearAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        yearAdapter.setOnItemListener((sortedIndex, switchValue, data) -> {
            ((SelectorBarItem) mYearData.get(oldYearIndex)).setSelected(false);
            int realIndex = yearAdapter.getRealIndex((int) sortedIndex);
            ((SelectorBarItem) mYearData.get(realIndex)).setSelected(true);
            oldYearIndex = realIndex;
        });
    }
}
