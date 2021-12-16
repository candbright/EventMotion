package com.example.app.manager;

import com.example.app.global.GlobalApp;

/**
 * <p>created by wyh in 2021/12/16</p>
 */
public class LocalFileManager {
    public static String getCacheDirAbsolutePath() {
        return GlobalApp.getInstance().getExternalCacheDir().getAbsolutePath();
    }
}
