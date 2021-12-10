package com.example.app.base.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * 事件动机模式的View模块：
 * 当包含者Activity需要调用ExternalRelations中的业务数据或方法时，则使用这个BaseFragment2，否则使用BaseFragment。
 */
public abstract class BaseFragment<ExternalRelations extends BaseFragmentExternalRelations> extends BaseToolFragment {

    private ExternalRelations mExternalRelations;
    private FragmentLifecycleListener mLifecycleListener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceID(), container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onCreateViewModule();
        mExternalRelations = newExternalRelations(); // new ExternalRelations(this) and  setLifecycleListener(), create modules, and set listeners for modules.
        if (mLifecycleListener != null) {
            mLifecycleListener.onModulesCreated();
        }
    }

    protected abstract int getLayoutResourceID();

    protected abstract void onCreateViewModule();

    protected abstract ExternalRelations newExternalRelations();

    public ExternalRelations getExternalRelations() {
        return mExternalRelations;
    }

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

