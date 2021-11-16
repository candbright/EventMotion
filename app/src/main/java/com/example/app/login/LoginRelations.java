package com.example.app.login;

import com.example.app.R;
import com.example.app.base.activity.ActivityLifecycleListener;
import com.example.app.base.activity.BaseExternalRelations;
import com.example.app.manager.FragmentsManager;

/**
 * created by wyh in 2021/11/16
 */
public class LoginRelations extends BaseExternalRelations<LoginActivity> {
    private FragmentsManager fragmentManager;
    public LoginRelations(LoginActivity activity) {
        super(activity);
        fragmentManager = new FragmentsManager(activity);
        RegisterFragment registerFragment = new RegisterFragment();
        fragmentManager.addFragmentToContainer(R.id.cl_container_empty, registerFragment);
    }

    @Override
    protected ActivityLifecycleListener newActivityLifecycleListener() {
        return new ActivityLifecycleListener(){
            @Override
            public void onModulesCreated() {
                super.onModulesCreated();

            }
        };
    }
}
