package com.example.app.base.fragment;

import android.widget.Toast;

/**
 * created by wyh in 2021/11/16
 */
public abstract class ToolFragment extends BaseFragment {

    public void showToast(String text) {
        showToast(text, true);
    }

    public void showToast(String text, boolean isShort) {
        if (isShort) {
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
        }
    }
}
