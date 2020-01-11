package com.nanchen.coordinatorlayoutdemo.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author mxb
 * @date 2019/4/26
 * @desc
 */
public class Behavior<V extends View> {

    public Behavior(Context context, AttributeSet attributeSet) {
    }


    public void onLayoutFinished(View child) {

    }

    public boolean onStartNestedScroll(MyCoordinatorLayout coordinatorLayout,
            V child, View directTargetChild, View target, int axes, int type) {
        return false;
    }

    public void onNestedScrollAccepted(MyCoordinatorLayout parent, @NonNull View child, @NonNull View target, int axes,
            int type) {
    }

    public void onStopNestedScroll(MyCoordinatorLayout parent, View child, @NonNull View target, int type) {
    }

    public void onNestedScroll(MyCoordinatorLayout parent, View child, @NonNull View target, int dxConsumed,
            int dyConsumed,
            int dxUnconsumed, int dyUnconsumed, int type) {

    }

    public void onNestedPreScroll(MyCoordinatorLayout parent, View child, @NonNull View target, int dx, int dy,
            @Nullable int[] consumed, int type) {

    }

    public boolean layoutDependsOn(MyCoordinatorLayout parent, V child, View dependency) {
        return false;
    }

    public boolean onDependentViewChanged(MyCoordinatorLayout parent, V child, View dependency) {
        return false;
    }
}
