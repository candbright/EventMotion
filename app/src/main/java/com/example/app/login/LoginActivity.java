package com.example.app.login;

import androidx.fragment.app.FragmentManager;

import com.example.app.R;
import com.example.app.base.activity.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_empty;
    }

    @Override
    protected void onCreateViewModule() {

    }

    @Override
    protected void newExternalRelations() {
        new LoginRelations(this);
    }
}