package com.nanchen.coordinatorlayoutdemo;

import com.nanchen.coordinatorlayoutdemo.test.MyCoordinatorActivity;
import com.nanchen.coordinatorlayoutdemo.test.NestedScrollActivity;
import com.nanchen.coordinatorlayoutdemo.touchevent.TouchEventActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick1(View view) {
        startActivity(new Intent(this, CoordinatorActivity.class));
    }

    public void btnClick2(View view) {
        startActivity(new Intent(this, CoorAppBarActivity.class));
    }

    public void btnClick3(View view) {
        startActivity(new Intent(this, CoorToolBarActivity.class));
    }

    public void btnClick4(View view) {
        // startActivity(new Intent(this, MyCoordinatorActivity.class));
        // startActivity(new Intent(this, NestedScrollActivity.class));
        startActivity(new Intent(this, NestedScrollActivity.class));
    }

    public void btnClick5(View view) {
        startActivity(new Intent(this, BottomSheetActivity.class));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    public void btnClick6(View view) {
        startActivity(new Intent(this, BottomSheetDialogActivity.class));
    }

    public void btnClick7(View view) {
        startActivity(new Intent(this, TouchEventActivity.class));
    }

    public void btnClick8(View view) {
        startActivity(new Intent(this, TopFlowItemActivity.class));
    }
}
