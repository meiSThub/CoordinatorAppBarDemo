package com.nanchen.coordinatorlayoutdemo.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author mxb
 * @date 2019/4/29
 * @desc
 */
public class ImageBehavior extends Behavior {

    private int originHeight;

    private int maxHeight = 400;

    private static final String TAG = "ImageBehavior";

    public ImageBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void onLayoutFinished(View child) {
        if (originHeight == 0) {
            originHeight = child.getMeasuredHeight();
        }
        Log.i(TAG, "onLayoutFinished: originHeight=" + originHeight);
    }

    @Override
    public boolean onStartNestedScroll(MyCoordinatorLayout coordinatorLayout, View child, View directTargetChild,
            View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScroll(MyCoordinatorLayout parent, View child, @NonNull View target, int dxConsumed,
            int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(parent, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        Log.i(TAG, "onNestedScroll: child=" + child + ";target=" + target);
        Log.i(TAG,
                "onNestedScroll: dyConsumed=" + dyConsumed + ";dyUnconsumed=" + dyUnconsumed + ";getScrollY()=" + target
                        .getScrollY());
        if (target.getScrollY() > 0) {
            MyCoordinatorLayout.LayoutParams layoutParams = (MyCoordinatorLayout.LayoutParams) child.getLayoutParams();
            layoutParams.height = layoutParams.height - Math.abs(dyConsumed);
            if (layoutParams.height < originHeight) {
                layoutParams.height = originHeight;
            }
            child.setLayoutParams(layoutParams);
        } else if (target.getScrollY() == 0) {
            MyCoordinatorLayout.LayoutParams layoutParams = (MyCoordinatorLayout.LayoutParams) child.getLayoutParams();
            layoutParams.height = layoutParams.height + Math.abs(dyUnconsumed);
            if (layoutParams.height > maxHeight) {
                layoutParams.height = maxHeight;
            }
            child.setLayoutParams(layoutParams);
        }

        // if (target.getScrollY() == 0) {
        //     Log.i(TAG, "onNestedScroll: scrollY=" + parent.getScrollY());
        //     if (parent.getScrollY() > 0) {
        //         // parent.scrollBy(dxUnconsumed, 0);
        //         parent.scrollBy(dxUnconsumed, dyUnconsumed);
        //     } else {
        //     }
        // } else if (parent.getScrollY() < originHeight) {
            parent.scrollBy(dxConsumed, dyConsumed);
        // }
    }
}
