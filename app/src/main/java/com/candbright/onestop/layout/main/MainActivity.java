package com.candbright.onestop.layout.main;

import com.candbright.onestop.R;
import com.candbright.onestop.base.activity.BaseExitActivity;
import com.candbright.onestop.databinding.ActivityMainBinding;
import com.candbright.onestop.databinding.NavigationBottomBarBinding;
import com.candbright.onestop.manager.ActivityFragmentManager;
import com.candbright.onestop.manager.NavigationBottomBarManager;


/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainActivity extends BaseExitActivity<ActivityMainBinding> {

    private static final String TAG = "<MainActivity>";

    private NavigationBottomBarBinding navigationBarBottom;
    private ActivityFragmentManager fragmentManager;
    private NavigationBottomBarManager navigationBottomBarManager;

    protected String getExitMessage() {
        return "再次点击后退出APP";
    }

    @Override
    protected void initViewBinding() {
        navigationBarBottom = NavigationBottomBarBinding.bind(rootBinding.getRoot());

    }

    @Override
    protected void initManager() {
        fragmentManager = new ActivityFragmentManager(this, R.id.fragment_container);
        fragmentManager.addOrReplaceFragment(new SongListFragment());
        navigationBottomBarManager = new NavigationBottomBarManager(navigationBarBottom);
        navigationBottomBarManager.setMusicButtonClickListener(v -> {
            fragmentManager.addOrReplaceFragment(new SongListFragment());
        });
        navigationBottomBarManager.setOfficialButtonClickListener(v -> {
            fragmentManager.addOrReplaceFragment(new SongOfficialFragment());
        });
        navigationBottomBarManager.setMidButtonClickListener(v -> {

        });
        navigationBottomBarManager.setTeachButtonClickListener(v -> {

        });
        navigationBottomBarManager.setCollectButtonClickListener(v -> {

        });
    }
}
