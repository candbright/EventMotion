package com.example.app.base.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app.R;

/**
 * 事件动机模式的View模块：
 * 当包含者Activity不需要调用ExternalRelations中的业务数据或方法时，则使用这个BaseFragment，否则使用BaseFragment2。
 */
public abstract class BaseFragment extends Fragment {

    private ViewGroup mInflateLayout;
    protected RelativeLayout mContainerLayout;
    private FragmentLifecycleListener mLifecycleListener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflateLayout = (ViewGroup) inflater.inflate(R.layout.fragment_base, container, false);
        mContainerLayout = mInflateLayout.findViewById(R.id.main_page_container);
        View inflateLayout = inflater.inflate(getLayoutResourceID(), null, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mContainerLayout.addView(inflateLayout, params);
        return mInflateLayout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onCreateViewModule();
        newExternalRelations(); // new ExternalRelations(this) and  setLifecycleListener(), create modules, and set listeners for modules.
        if (mLifecycleListener != null) {
            mLifecycleListener.onModulesCreated();
        }
    }

    protected abstract int getLayoutResourceID();
    protected abstract void onCreateViewModule();
    protected abstract void newExternalRelations();

    protected void setLifecycleListener(FragmentLifecycleListener activityLifecycleListener) {
        mLifecycleListener = activityLifecycleListener;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mLifecycleListener != null) {
            mLifecycleListener.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mLifecycleListener != null) {
            mLifecycleListener.onPause();
        }
        super.onPause();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (mLifecycleListener != null) {
            mLifecycleListener.onHiddenChanged(hidden);
        }
    }

    @Override
    public void onDestroyView() {
        if (mLifecycleListener != null) {
            mLifecycleListener.onDestroyView();
        }
        super.onDestroyView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (mLifecycleListener != null) {
            mLifecycleListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mLifecycleListener != null) {
            mLifecycleListener.onActivityResult(requestCode, resultCode, data);
        }
    }
}

