package com.example.app.manager;

import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app.R;
import com.example.app.base.activity.BaseActivity;
import com.example.app.base.fragment.BaseFragment;

/**
 * created by wyh in 2021/11/16
 */
public class FragmentsManager<Activity extends BaseActivity> {
    private Activity activity;
    private FragmentManager fragmentManager;
    public FragmentsManager(Activity activity) {
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
