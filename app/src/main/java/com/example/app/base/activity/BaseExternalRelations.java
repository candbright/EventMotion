package com.example.app.base.activity;

/**
 * created by wyh in 2021/11/15
 */
public class BaseExternalRelations<Activity extends BaseActivity> {
    protected Activity mActivity;
    public BaseExternalRelations(Activity activity) {
        mActivity = activity;
        mActivity.setLifecycleListener(newActivityLifecycleListener());
    }
    protected ActivityLifecycleListener newActivityLifecycleListener() {
        return new ActivityLifecycleListener(){
        };
    }
}
