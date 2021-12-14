package com.example.app.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app.R;
import com.example.app.base.view.BaseConstraintLayout;
import com.example.app.global.GlobalApp;

import java.util.ArrayList;

/**
 * <p>created by wyh in 2021/12/14</p>
 */
public class WaveConstraintLayout extends BaseConstraintLayout {

    private float circleCenterX;
    private float circleCenterY;
    private float rawRadius;
    private float drewRadius = 0;
    private float drawingRadiusDegrees = 5;
    private View mTargetTouchView;
    private RectF targetTouchRectF;
    private float[] mDownPosition;
    private static final int INVALID_DURATION = 1000;
    private postUpEventDelayed delayedRunnable = new postUpEventDelayed();

    public WaveConstraintLayout(@NonNull Context context) {
        super(context);
    }

    public WaveConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WaveConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        /**
         * 绘制完子元素后开始绘制波纹
         */
        if (mTargetTouchView != null) {
            canvas.save();
            // 为了不让绘制的圆环超出所要绘制的范围
            if (drewRadius < rawRadius) {
                drewRadius += rawRadius / drawingRadiusDegrees;
                Paint mHalfTransPaint = new Paint();
                mHalfTransPaint.setAntiAlias(true);
                mHalfTransPaint.setColor(GlobalApp.getInstance().getApplicationContext().getResources().getColor(R.color.common_blue, null));
                canvas.drawCircle(mDownPosition[0], mDownPosition[1], drewRadius, mHalfTransPaint);
                postInvalidateDelayed(INVALID_DURATION);
            } else {
                Paint mTransPaint = new Paint();
                mTransPaint.setAntiAlias(true);
                mTransPaint.setColor(GlobalApp.getInstance().getResources().getColor(R.color.common_blue, null));
                canvas.drawCircle(mDownPosition[0], mDownPosition[1], rawRadius, mTransPaint);
                post(delayedRunnable);
                drewRadius = 0;
            }
            canvas.restore();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTargetTouchView = findTargetView(ev.getRawX(), ev.getRawY(), this);
                if (mTargetTouchView != null) {
                    mDownPosition = getCircleCenterPosition(ev.getRawX(), ev.getRawY());
                    circleCenterX = mDownPosition[0];
                    circleCenterY = mDownPosition[1];
                    float left = circleCenterX - targetTouchRectF.left;
                    float right = targetTouchRectF.right - circleCenterX;
                    float top = circleCenterY - targetTouchRectF.top;
                    float bottom = targetTouchRectF.bottom - circleCenterY;
                    // 计算出最大的值则为半径
                    rawRadius = Math.max(bottom, Math.max(Math.max(left, right), top));
                }
                break;
            case MotionEvent.ACTION_UP:
                delayedRunnable.event = ev;
                return super.dispatchTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public View findTargetView(float x, float y, View anchorView) {
        ArrayList<View> touchableView = anchorView.getTouchables();
        View targetView = null;
        for (View child : touchableView) {
            targetTouchRectF = getViewRectF(child);
            if (targetTouchRectF.contains(x, y) && child.isClickable()) {
                // 这说明被点击的view找到了
                targetView = child;
                break;
            }
        }
        return targetView;
    }

    public RectF getViewRectF(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int childLeft = location[0];
        int childTop = location[1];
        int childRight = childLeft + view.getMeasuredWidth();
        int childBottom = childTop + view.getMeasuredHeight();
        return new RectF(childLeft, childTop, childRight, childBottom);
    }

    public float[] getCircleCenterPosition(float x, float y) {
        int[] location = new int[2];
        float[] mDownPosition = new float[2];
        getLocationOnScreen(location);
        mDownPosition[0] = x;
        mDownPosition[1] = y -location[1];
        return mDownPosition;
    }

    class postUpEventDelayed implements Runnable {
        public MotionEvent event;

        @Override
        public void run() {
            if (mTargetTouchView != null && mTargetTouchView.isClickable()
                    && event != null) {
                mTargetTouchView.dispatchTouchEvent(event);// 分发
            }
        }
    }
}
