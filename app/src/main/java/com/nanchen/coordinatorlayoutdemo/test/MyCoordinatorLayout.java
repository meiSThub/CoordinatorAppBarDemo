package com.nanchen.coordinatorlayoutdemo.test;

import com.nanchen.coordinatorlayoutdemo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import java.lang.reflect.Constructor;

/**
 * @author mxb
 * @date 2019/4/26
 * @desc 手写CoordinatorLayout
 */
public class MyCoordinatorLayout extends LinearLayout implements NestedScrollingParent2,
        ViewTreeObserver.OnGlobalLayoutListener {

    private static final String TAG = "MyCoordinatorLayout";

    private NestedScrollingParentHelper mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);


    public MyCoordinatorLayout(Context context) {
        super(context);
    }

    public MyCoordinatorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCoordinatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        boolean handled = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams.mBehavior != null) {
                handled |= layoutParams.mBehavior
                        .onStartNestedScroll(this, view, child, target, axes, type);
            }
        }
        return handled;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        mNestedScrollingParentHelper.onStopNestedScroll(target, type);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            if (layoutParams.mBehavior != null) {
                layoutParams.mBehavior
                        .onStopNestedScroll(this, child, target, type);
            }
        }
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed,
            int type) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            // Log.i(TAG, "onNestedScroll: behavior=" + layoutParams.mBehavior);
            if (layoutParams.mBehavior != null) {
                layoutParams.mBehavior
                        .onNestedScroll(this, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
            }
        }

        // Log.i(TAG, "onNestedScroll:start scrollY=" + getScrollY() + ";scrollView_scrollY=" + target.getScrollY());
        // scrollBy(dxConsumed, dyConsumed);
        // Log.i(TAG, "onNestedScroll:  end scrollY=" + getScrollY() + ";scrollView_scrollY=" + target.getScrollY());
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @Nullable int[] consumed, int type) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            if (layoutParams.mBehavior != null) {
                layoutParams.mBehavior
                        .onNestedPreScroll(this, child, target, dx, dy, consumed, type);
            }
        }
    }

    @Override
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            if (layoutParams.mBehavior != null) {
                layoutParams.mBehavior.onLayoutFinished(child);
            }
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {

        private Behavior mBehavior;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.MyCoordinatorLayout);
            if (typedArray.hasValue(R.styleable.MyCoordinatorLayout_layout_behavior)) {
                mBehavior = parseBehavior(c, attrs,
                        typedArray.getString(R.styleable.MyCoordinatorLayout_layout_behavior));
            }
        }

        private Behavior parseBehavior(Context context, AttributeSet attrs, String name) {
            if (TextUtils.isEmpty(name)) {
                return null;
            }

            final String fullName;
            if (name.startsWith(".")) {
                // Relative to the app package. Prepend the app package name.
                fullName = context.getPackageName() + name;
            } else if (name.indexOf('.') >= 0) {
                // Fully qualified package name.
                fullName = name;
            } else {
                // Assume stock behavior in this package (if we have one)
                final Package pkg = CoordinatorLayout.class.getPackage();
                String packageName = pkg != null ? pkg.getName() : null;
                fullName = !TextUtils.isEmpty(packageName) ? (packageName + '.' + name) : name;
            }

            try {

                final Class<Behavior> clazz = (Class<Behavior>) Class
                        .forName(fullName, true, context.getClassLoader());
                Constructor<Behavior> c = clazz.getConstructor(new Class<?>[]{
                        Context.class,
                        AttributeSet.class
                });
                c.setAccessible(true);
                return c.newInstance(context, attrs);
            } catch (Exception e) {
                throw new RuntimeException("Could not inflate Behavior subclass " + fullName, e);
            }

        }
    }
}
