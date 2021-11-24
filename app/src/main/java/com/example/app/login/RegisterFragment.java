package com.example.app.login;


import android.graphics.Typeface;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.app.R;
import com.example.app.base.fragment.ToolFragment;

/**
 * created by wyh in 2021/10/26
 */
public class RegisterFragment extends ToolFragment {
    private static final String TAG = "RegisterFragment";
    private EditText usernameEdit;
    private EditText passwordEdit;
    private EditText passwordConfirmEdit;
    private AppCompatButton saveButton;

    @Override
    protected int getLayoutResourceID() {
        return R.layout.fragment_register;
    }

    @Override
    protected void onCreateViewModule() {
        initView();
    }

    private void initView() {
        View rootView = getView();
        if (rootView == null) {
            return;
        }
        usernameEdit = rootView.findViewById(R.id.et_frag_reg_username);
        usernameEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        passwordEdit = rootView.findViewById(R.id.et_frag_reg_password);
        passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordEdit.setTypeface(Typeface.DEFAULT);
        passwordEdit.setTransformationMethod(new PasswordTransformationMethod());
        passwordEdit.setOnEditorActionListener((v, actionId, event) -> false);
        passwordConfirmEdit = rootView.findViewById(R.id.et_frag_confirm_password);
        passwordConfirmEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordConfirmEdit.setTypeface(Typeface.DEFAULT);
        passwordConfirmEdit.setTransformationMethod(new PasswordTransformationMethod());
        saveButton = rootView.findViewById(R.id.btn_frag_reg);
    }

    @Override
    protected void newExternalRelations() {
        new RegisterFragmentRelations(this);
    }

    public void setSaveListener(View.OnClickListener onClickListener) {
        saveButton.setOnClickListener(onClickListener);
    }

    public void setPasswordEditAction(TextView.OnEditorActionListener actionListener) {
        passwordConfirmEdit.setOnEditorActionListener(actionListener);
    }
}
