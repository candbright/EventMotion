package com.candbright.onestop.base.activity;

public interface IActivityLifecycleListener {
    void onViewCreated();

    void onResume();

    void onPause();

    void onDestroy();

    /**
     * 返回true，则调用super方法。返回false，则不调用。
     */
    boolean onBackPressed();

    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
}
