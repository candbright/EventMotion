package com.example.app.base.activity;

import android.widget.Toast;

import androidx.viewbinding.ViewBinding;

import java.util.Timer;
import java.util.TimerTask;

/**
 * created by wyh in 2021/11/16
 */
public abstract class BaseExitActivity<BindingView extends ViewBinding> extends BaseActivity<BindingView> {

    private Toast exitToast;

    private boolean isExit;

    public void exit() {
        if (exitToast == null) {
            exitToast = Toast.makeText(this, "再次点击后退出APP", Toast.LENGTH_SHORT);
        }
        exitToast.show();
    }

    public void realExit() {
        if (exitToast == null) {
            return;
        }
        exitToast.cancel();
    }

    @Override
    public void onBackPressed() {
        if (isExit) {
            realExit();
            super.onBackPressed();
            finish();
            return;
        }
        exit();
        isExit = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isExit = false;
            }
        }, 2000);
    }
}
