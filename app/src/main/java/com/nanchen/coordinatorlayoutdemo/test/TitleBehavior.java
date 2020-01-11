package com.nanchen.coordinatorlayoutdemo.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author mxb
 * @date 2019/4/28
 * @desc
 */
public class TitleBehavior extends Behavior {

    public TitleBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onDependentViewChanged(MyCoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(MyCoordinatorLayout parent, View child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }
}
