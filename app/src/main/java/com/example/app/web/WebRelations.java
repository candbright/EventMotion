package com.example.app.web;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;

import com.example.app.R;
import com.example.app.base.activity.ActivityLifecycleListener;
import com.example.app.base.activity.BaseExternalRelations;
import com.example.app.global.GlobalApp;
import com.example.app.manager.NavigationBarManager;
import com.example.app.manager.WebViewManager;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;

/**
 * <p>created by wyh in 2021/12/16</p>
 */
public class WebRelations extends BaseExternalRelations<WebActivity> {
    private static final String TAG = WebRelations.class.getSimpleName();
    private NavigationBarManager navigationBarManager;
    private WebViewManager webViewManager;
    private boolean isFirstConnect = true;

    public WebRelations(WebActivity activity) {
        super(activity);
        navigationBarManager = new NavigationBarManager(activity.navigationBar, NavigationBarManager.MODE_NAV_TOP_NO_RIGHT_IMAGE);
        navigationBarManager.setTitle(GlobalApp.getResString(R.string.title_loading));
        navigationBarManager.setLeftImageSrc(R.drawable.navigation_back).setLeftOnClickListener(v -> activity.onBackPressed());
        webViewManager = new WebViewManager(activity.getRootBinding().webView, new BridgeWebViewClient(activity.getRootBinding().webView) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, url);
                if (url.contains("bilibili")) {
                    navigationBarManager.setTitle(GlobalApp.getResString(R.string.bilibili_name));
                    if (url.contains("video") && !isFirstConnect) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                        return false;
                    }
                    if (url.contains("search")) {
                        activity.loadWebViewWithURL(url);
                        return true;
                    }
                }
                if (url.contains("iqiyi")) {
                    navigationBarManager.setTitle(GlobalApp.getResString(R.string.iqiyi_name));
                    if (url.contains("video") && !isFirstConnect) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);
                        return false;
                    }
                    if (url.contains("search")) {
                        activity.loadWebViewWithURL(url);
                        return true;
                    }
                }
                isFirstConnect = false;
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
    }

    @Override
    protected ActivityLifecycleListener newActivityLifecycleListener() {
        return new ActivityLifecycleListener() {
            @Override
            public void onModulesCreated() {
                super.onModulesCreated();
                mActivity.parseIntent();
            }

            @Override
            public boolean onBackPressed() {
                if (null == webViewManager.webView) {

                    return super.onBackPressed();
                }
                if (webViewManager.webView.canGoBack()) {
                    webViewManager.webView.goBack();
                    return false;
                } else {
                    return super.onBackPressed();
                }
            }
        };
    }
}
