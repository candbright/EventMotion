package com.example.app.login;


import com.example.app.R;
import com.example.app.base.fragment.BaseFragment;

import android.graphics.Typeface;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

/**
 * created by wyh in 2021/10/26
 */
public class RegisterFragment extends BaseFragment {
    private EditText usernameEdit;
    private EditText passwordEdit;
    private EditText passwordConfirmEdit;
    @Override
    protected int getLayoutResourceID() {
        return R.layout.fragment_register;
    }

    @Override
    protected void onCreateViewModule() {
        initView();
    }

    private void initView() {
        usernameEdit = getView().findViewById(R.id.et_frag_reg_username);
        usernameEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        passwordEdit = getView().findViewById(R.id.et_frag_reg_password);
        passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordEdit.setTypeface(Typeface.DEFAULT);
        passwordEdit.setTransformationMethod(new PasswordTransformationMethod());
        passwordConfirmEdit = getView().findViewById(R.id.et_frag_confirm_password);
        passwordConfirmEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordConfirmEdit.setTypeface(Typeface.DEFAULT);
        passwordConfirmEdit.setTransformationMethod(new PasswordTransformationMethod());
    }

    @Override
    protected void newExternalRelations() {

    }
}
