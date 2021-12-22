package com.example.app.main;

import com.example.app.R;
import com.example.app.base.fragment.BaseFragment;
import com.example.app.databinding.FragmentSongOfficialBinding;
import com.example.app.databinding.NavigationBarBinding;
import com.example.app.manager.NavigationBarManager;

/**
 * <p>created by wyh in 2021/12/22</p>
 */
public class SongOfficialFragment extends BaseFragment<FragmentSongOfficialBinding> {
    private NavigationBarBinding navigationBarTop;
    private NavigationBarManager navigationBarTopManager;
    @Override
    protected void initViewBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
    }

    @Override
    protected void initManager() {
        navigationBarTopManager = new NavigationBarManager(navigationBarTop);
        navigationBarTopManager.setRightImageSrc(R.drawable.navigation_calendar);
        navigationBarTopManager.setTitle(R.string.title_official_song);
    }
}
