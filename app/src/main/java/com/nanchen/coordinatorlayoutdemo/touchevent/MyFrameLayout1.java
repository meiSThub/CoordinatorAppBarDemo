package com.nanchen.coordinatorlayoutdemo.touchevent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author mxb
 * @date 2019/7/16
 * @desc
 * @desired
 */
public class MyFrameLayout1 extends FrameLayout {

    private static final String TAG = "MyFrameLayout1";

    public MyFrameLayout1(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout1(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: ev=" + ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: ev=" + ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: event=" + event);
        return super.onTouchEvent(event);
    }
}
