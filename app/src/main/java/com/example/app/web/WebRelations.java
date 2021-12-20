package com.example.app.web;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
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
        activity.getRootBinding().webView.setDownloadListener((url, userAgent, contentDisposition, mimetype, contentLength) -> {
            // 上面的参数中，url对应文件下载地址，mimetype对应下载文件的MIME类型
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(url);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(uri);
            activity.startActivity(intent);
        });
        webViewManager = new WebViewManager(activity.getRootBinding().webView, new BridgeWebViewClient(activity.getRootBinding().webView) {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                Log.d(TAG, "URL: " + url);
                WebView.HitTestResult hit = view.getHitTestResult();
                //hit.getExtra()为null或者hit.getType() == 0都表示即将加载的URL会发生重定向，需要做拦截处理
                if (TextUtils.isEmpty(hit.getExtra()) || hit.getType() == 0) {
                    //通过判断开头协议就可解决大部分重定向问题了，有另外的需求可以在此判断下操作
                    Log.e(TAG, "重定向: " + hit.getType() + " && EXTRA（）" + hit.getExtra() + "------");
                    Log.e(TAG, "GetURL: " + view.getUrl() + "\n" + "getOriginalUrl()" + view.getOriginalUrl());
                    Log.d(TAG, "URL: " + url);
                }

                if (url.startsWith("http://") || url.startsWith("https://")) { //加载的url是http/https协议地址
                    activity.loadWebViewWithURL(url);
                    return false; //返回false表示此url默认由系统处理,url未加载完成，会继续往下走

                } else { //加载的url是自定义协议地址
                    try {
                        if (url.contains("bilibili")) {
                            navigationBarManager.setTitle(GlobalApp.getResString(R.string.bilibili_name));
                            if (url.contains("?page=0")) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.startActivity(intent);
                                return true;
                            }
                        }
                        if (url.contains("iqiyi")) {
                            navigationBarManager.setTitle(GlobalApp.getResString(R.string.iqiyi_name));
                            if (url.contains("&subtype=149")) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.startActivity(intent);
                            }
                        }
                        if (url.contains("youku")) {
                            navigationBarManager.setTitle(GlobalApp.getResString(R.string.youku_name));
                            if (url.contains("&callup_type=clk")) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                activity.startActivity(intent);
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "StartActivity Error: " + "You haven't installed this software on your phone yet.");
                    }
                    return true;
                }

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
