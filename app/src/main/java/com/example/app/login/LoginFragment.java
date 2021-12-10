package com.example.app.login;

import com.example.app.R;
import com.example.app.base.fragment.BaseFragmentExternalRelations;
import com.example.app.base.fragment.BaseToolFragment;

/**
 * <p>created by wyh in 2021/10/26</p>
 */
public class LoginFragment extends BaseToolFragment {
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
    protected BaseFragmentExternalRelations newExternalRelations() {

        return null;
    }
}
