package com.example.app.manager;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app.base.activity.BaseActivity;
import com.example.app.base.fragment.BaseFragment;

/**
 * <p>created by wyh in 2021/11/16</p>
 */
public class ActivityFragmentManager<Activity extends BaseActivity> {
    private Activity activity;
    private FragmentManager fragmentManager;

    public ActivityFragmentManager(Activity activity) {
        this.activity = activity;
        fragmentManager = activity.getSupportFragmentManager();
    }

    public void addFragmentToContainer(int containerViewId, BaseFragment fragment) {

        if (null == fragment) {
            return;
        }

        if (null == fragmentManager) {
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerViewId, fragment, fragment.getClass().getName());
        transaction.commit();
    }
}
