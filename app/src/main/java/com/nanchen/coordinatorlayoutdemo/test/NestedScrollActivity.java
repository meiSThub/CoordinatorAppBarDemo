package com.nanchen.coordinatorlayoutdemo.test;

import com.nanchen.coordinatorlayoutdemo.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * @author mxb
 * @date 2019/4/29
 * @desc
 */
public class NestedScrollActivity extends AppCompatActivity {

    ViewPager mViewPager;

    TabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            Fragment[] mFragments = new Fragment[3];

            String[] mTitles = new String[]{"简介", "评价", "相关"};

            @Override
            public Fragment getItem(int position) {
                if (mFragments[position] == null) {
                    mFragments[position] = NestedFragment.getInstance();
                }
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = mTabLayout.getSelectedTabPosition();
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
