package com.example.app.login;


import android.graphics.Typeface;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.TextView;

import com.example.app.base.fragment.BaseFragment;
import com.example.app.databinding.FragmentRegisterBinding;

/**
 * <p>created by wyh in 2021/10/26</p>
 */
public class RegisterFragment extends BaseFragment<RegisterFragmentRelations, FragmentRegisterBinding> {
    private static final String TAG = "RegisterFragment";

    @Override
    protected void onCreateViewModule() {
        initView();
    }

    private void initView() {
        View rootView = getView();
        if (rootView == null) {
            return;
        }

        viewBinding.usernameEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        viewBinding.passwordEdit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        viewBinding.passwordEdit.setTypeface(Typeface.DEFAULT);
        viewBinding.passwordEdit.setTransformationMethod(new PasswordTransformationMethod());
        viewBinding.passwordEdit.setOnEditorActionListener((v, actionId, event) -> false);
        viewBinding.passwordConfirmEdit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        viewBinding.passwordConfirmEdit.setTypeface(Typeface.DEFAULT);
        viewBinding.passwordConfirmEdit.setTransformationMethod(new PasswordTransformationMethod());
    }

    @Override
    protected RegisterFragmentRelations newExternalRelations() {
        return new RegisterFragmentRelations(this);
    }

    public void setSaveListener(View.OnClickListener onClickListener) {
        viewBinding.saveButton.setOnClickListener(onClickListener);
    }

    public void setPasswordEditAction(TextView.OnEditorActionListener actionListener) {
        viewBinding.passwordConfirmEdit.setOnEditorActionListener(actionListener);
    }
}
