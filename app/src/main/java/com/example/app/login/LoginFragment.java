package com.example.app.login;

import androidx.fragment.app.Fragment;

import com.example.app.R;
import com.example.app.base.fragment.ToolFragment;

/**
 * created by wyh in 2021/10/26
 */
public class LoginFragment extends ToolFragment {
    @Override
    protected int getLayoutResourceID() {
        return R.layout.fragment_register;
    }

    @Override
    protected void onCreateViewModule() {
        initView();
    }

    private void initView() {

    }

    @Override
    protected void newExternalRelations() {

    }
}
