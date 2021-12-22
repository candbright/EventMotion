package com.example.app.main;

import com.example.app.R;
import com.example.app.base.activity.BaseExitActivity;
import com.example.app.databinding.ActivityMainBinding;
import com.example.app.databinding.NavigationBottomBarBinding;
import com.example.app.manager.ActivityFragmentManager;
import com.example.app.manager.NavigationBottomBarManager;


/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainActivity extends BaseExitActivity<ActivityMainBinding> {

    private static final String TAG = "<MainActivity>";

    private NavigationBottomBarBinding navigationBarBottom;
    private ActivityFragmentManager fragmentManager;
    private NavigationBottomBarManager navigationBottomBarManager;

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
