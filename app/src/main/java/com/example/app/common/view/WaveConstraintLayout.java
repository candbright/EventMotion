package com.example.app.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
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

    private static final String TAG = WaveConstraintLayout.class.getSimpleName();
    private float circleCenterX;
    private float circleCenterY;
    private float rawRadius;
    private float drewRadius = 0;
    private float drawingRadiusDegrees = 20;
    private View mTargetTouchView;
    private RectF targetTouchRectF;
    private float[] mDownPosition;
    private static final int INVALID_DURATION = 10;
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
            
            if (drewRadius < rawRadius) {
                drewRadius += rawRadius / drawingRadiusDegrees;
                // 为了不让绘制的圆环超出所要绘制的范围
                Paint mHalfTransPaint = new Paint();
                mHalfTransPaint.setAntiAlias(true);
                mHalfTransPaint.setColor(GlobalApp.getInstance().getApplicationContext().getResources().getColor(R.color.common_gray_AA, null));
                canvas.drawCircle(circleCenterX, circleCenterY, drewRadius, mHalfTransPaint);
                postInvalidateDelayed(INVALID_DURATION);
            } else {
                Paint mTransPaint = new Paint();
                mTransPaint.setAntiAlias(true);
                mTransPaint.setColor(GlobalApp.getInstance().getResources().getColor(R.color.common_gray_AA, null));
                canvas.drawCircle(circleCenterX, circleCenterY, rawRadius, mTransPaint);
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
                Log.d(TAG, "getRawX: " + ev.getRawX());
                Log.d(TAG, "getRawY: " + ev.getRawY());
                mTargetTouchView = findTargetView(ev.getRawX(), ev.getRawY(), this);
                if (mTargetTouchView != null) {
                    mDownPosition = getCircleCenterPosition(ev.getRawX(), ev.getRawY());
                    float left = mDownPosition[0] - targetTouchRectF.left;
                    float right = targetTouchRectF.right - mDownPosition[0];
                    float top = mDownPosition[1] - targetTouchRectF.top;
                    float bottom = targetTouchRectF.bottom - mDownPosition[1];
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
            Log.d(TAG, targetTouchRectF.toString());
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
        mDownPosition[1] = y;
        circleCenterX = x;
        circleCenterY = y - location[1];
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
