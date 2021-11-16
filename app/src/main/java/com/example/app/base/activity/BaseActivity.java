package com.example.app.base.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * created by wyh in 2021/11/15
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ActivityLifecycleListener mLifecycleListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceID());
        onCreateViewModule();
        newExternalRelations(); // new ExternalRelations(this) and  setLifecycleListener(), create modules, and set listeners for modules.
        if (mLifecycleListener != null) {
            mLifecycleListener.onModulesCreated();
        }
    }

    protected abstract int getLayoutResourceID();

    //创建模块之前
    protected abstract void onCreateViewModule();

    //外部关系模块
    protected abstract void newExternalRelations();

    protected void setLifecycleListener(ActivityLifecycleListener activityLifecycleListener) {
        mLifecycleListener = activityLifecycleListener;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLifecycleListener != null) {
            mLifecycleListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mLifecycleListener != null) {
            mLifecycleListener.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLifecycleListener != null) {
            mLifecycleListener.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mLifecycleListener != null) {
            mLifecycleListener.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mLifecycleListener != null) {
            mLifecycleListener.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    // onPause(), onDestroy(), onRequestPermissionsResult()等类似于onResume()一样
}