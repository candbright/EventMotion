package com.example.app.web;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;

import com.example.app.base.activity.BaseActivity;
import com.example.app.databinding.ActivityWebBinding;
import com.example.app.databinding.NavigationBarBinding;

/**
 * <p>created by wyh in 2021/12/16</p>
 */
public class WebActivity extends BaseActivity<ActivityWebBinding> {
    public static final String WEB_URL_KEY = "WEB_URL_KEY";
    NavigationBarBinding navigationBar;
    @Override
    protected void onCreateViewModule() {
        initBinding();
    }

    private void initBinding() {
        navigationBar = NavigationBarBinding.bind(rootView.getRoot());
    }

    @Override
    protected void newExternalRelations() {
        new WebRelations(this);
    }

    public void parseIntent() {

        Intent intent = getIntent();
        if (null == intent) {
            return;
        }

        Bundle intentBundle = intent.getExtras();
        if (null == intentBundle) {
            return;
        }

        // add your intent parse here
        String url = intentBundle.getString(WEB_URL_KEY);
        if (null == url) {
            return;
        }
        loadWebViewWithURL(url);
        //
        setIntent(null);
    }

    public void loadWebViewWithURL(String url) {

        if (null == url || url.isEmpty()) {
            return;
        }

        if (null != rootView.webView) {
            CookieManager.getInstance().acceptCookie();
            rootView.webView.loadUrl(url);
        }
    }
}
