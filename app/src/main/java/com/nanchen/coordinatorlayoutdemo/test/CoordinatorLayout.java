package com.nanchen.coordinatorlayoutdemo.test;

import com.nanchen.coordinatorlayoutdemo.R;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import java.lang.reflect.Constructor;

/**
 * @author mxb
 * @date 2019/4/26
 * @desc 手写CoordinatorLayout
 */
public class CoordinatorLayout extends LinearLayout implements NestedScrollingParent {

    private static final String TAG = "CoordinatorLayout";

    private View mTopView;

    private View mViewPager;

    private int mTopViewHeight;

    private ValueAnimator mOffsetAnimator;

    private Interpolator mInterpolator;

    private int TOP_CHILD_FLING_THRESHOLD = 3;


    public CoordinatorLayout(Context context) {
        super(context);
    }

    public CoordinatorLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // boolean hideTop = dy > 0 && getScrollY() < mTopViewHeight;
        // boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);
        // Log.i(TAG, "onNestedPreScroll: hideTop=" + hideTop + ";showTop=" + showTop + ";dy=" + dy + ";getScrollY()="
        //         + getScrollY());
        // if (hideTop || showTop) {
        //     scrollBy(0, dy);
        //     consumed[1] = dy;
        // }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        boolean hideTop = dyUnconsumed > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dyUnconsumed < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);
        Log.i(TAG, "onNestedPreScroll: hideTop=" + hideTop + ";showTop=" + showTop + ";dy=" + dyUnconsumed + ";getScrollY()="
                + getScrollY());
        if (hideTop || showTop) {
            scrollBy(0, dyUnconsumed);
            // consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        //如果是recyclerView 根据判断第一个元素是哪个位置可以判断是否消耗
        //这里判断如果第一个元素的位置是大于TOP_CHILD_FLING_THRESHOLD的
        //认为已经被消耗，在animateScroll里不会对velocityY<0时做处理
        // if (target instanceof RecyclerView && velocityY < 0) {
        //     final RecyclerView recyclerView = (RecyclerView) target;
        //     final View firstChild = recyclerView.getChildAt(0);
        //     final int childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild);
        //     consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD;
        // }
        // if (!consumed) {
        //     animateScroll(velocityY, computeDuration(0), consumed);
        // } else {
        //     animateScroll(velocityY, computeDuration(velocityY), consumed);
        // }
        return true;
    }

    @Override
    public LinearLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = findViewById(R.id.id_top_view);
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTopView.getMeasuredHeight();
        Log.i(TAG, "onSizeChanged: mTopViewHeight=" + mTopViewHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        LayoutParams layoutParams = (LayoutParams) mViewPager.getLayoutParams();
        layoutParams.height = getMeasuredHeight() - mTopView.getHeight();
        // mViewPager.setLayoutParams(layoutParams);
        setMeasuredDimension(getMeasuredWidth(), mTopView.getMeasuredHeight() + mViewPager.getMeasuredHeight());
    }

    @Override
    public void scrollTo(int x, int y) {

        if (y < 0) {
            y = 0;
        }

        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }

        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    private void animateScroll(float velocityY, final int duration, boolean consumed) {
        final int currentOffset = getScrollY();
        final int topHeight = mTopView.getHeight();
        if (mOffsetAnimator == null) {
            mOffsetAnimator = new ValueAnimator();
            mOffsetAnimator.setInterpolator(mInterpolator);
            mOffsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (animation.getAnimatedValue() instanceof Integer) {
                        scrollTo(0, (Integer) animation.getAnimatedValue());
                    }
                }
            });
        } else {
            mOffsetAnimator.cancel();
        }
        mOffsetAnimator.setDuration(Math.min(duration, 600));

        if (velocityY >= 0) {
            mOffsetAnimator.setIntValues(currentOffset, topHeight);
            mOffsetAnimator.start();
        } else {
            //如果子View没有消耗down事件 那么就让自身滑倒0位置
            if (!consumed) {
                mOffsetAnimator.setIntValues(currentOffset, 0);
                mOffsetAnimator.start();
            }

        }
    }

    /**
     * 根据速度计算滚动动画持续时间
     */
    private int computeDuration(float velocityY) {
        final int distance;
        if (velocityY > 0) {
            distance = Math.abs(mTopView.getHeight() - getScrollY());
        } else {
            distance = Math.abs(mTopView.getHeight() - (mTopView.getHeight() - getScrollY()));
        }

        final int duration;
        velocityY = Math.abs(velocityY);
        if (velocityY > 0) {
            duration = 3 * Math.round(1000 * (distance / velocityY));
        } else {
            final float distanceRatio = (float) distance / getHeight();
            duration = (int) ((distanceRatio + 1) * 150);
        }

        return duration;

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
