package com.example.app.login;

import android.view.inputmethod.EditorInfo;

import com.example.app.base.fragment.BaseFragmentExternalRelations;

/**
 * <p>created by wyh in 2021/11/16</p>
 */
public class RegisterFragmentRelations extends BaseFragmentExternalRelations<RegisterFragment> {
    public RegisterFragmentRelations(RegisterFragment fragment) {
        super(fragment);
        fragment.setSaveListener(v -> beginRegister(fragment));
        fragment.setPasswordEditAction((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                beginRegister(fragment);
            }
            //false: 隐藏软键盘
            return false;
        });
    }

    public void beginRegister(RegisterFragment fragment) {
        fragment.showToast("register");
    }
}
