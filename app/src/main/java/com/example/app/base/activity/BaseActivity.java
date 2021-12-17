package com.example.app.base.activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * created by wyh in 2021/11/15
 */
public abstract class BaseActivity<BindingView extends ViewBinding> extends AppCompatActivity {
    private ActivityLifecycleListener mLifecycleListener;
    protected BindingView rootBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBinding();
        onCreateViewModule();
        newExternalRelations(); // new ExternalRelations(this) and  setLifecycleListener(), create modules, and set listeners for modules.
        if (mLifecycleListener != null) {
            mLifecycleListener.onModulesCreated();
        }
    }

    protected void initViewBinding() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class cls = (Class) type.getActualTypeArguments()[0];
        try {
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class);
            rootBinding = (BindingView) inflate.invoke(null, getLayoutInflater());
            setContentView(rootBinding.getRoot());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public BindingView getRootBinding() {
        return rootBinding;
    }

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
        if (mLifecycleListener != null) {
            if (mLifecycleListener.onBackPressed()) {
                super.onBackPressed();
            }
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