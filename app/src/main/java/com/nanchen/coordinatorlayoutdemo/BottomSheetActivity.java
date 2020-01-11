package com.nanchen.coordinatorlayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author mxb
 * @date 2019/5/8
 * @desc
 * @desired
 */
public class BottomSheetActivity extends AppCompatActivity {

    View mView;

    BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        mView = findViewById(R.id.tv_bottom);
        mBottomSheetBehavior = BottomSheetBehavior.from(mView);
    }

    public void intro(View view) {
        if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }

        ViewCompat.offsetTopAndBottom(view, 10);
    }
}
