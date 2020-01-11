package com.nanchen.coordinatorlayoutdemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 悬浮置顶
 */
public class TopFlowItemActivity extends AppCompatActivity {

    private static final String TAG = "TopFlowItemActivity";

    RecyclerView mRecyclerView;

    LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_flow_item);

        mLayoutInflater = getLayoutInflater();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View root = mLayoutInflater.inflate(R.layout.item_top_flow_activity, parent, false);
                ViewHolder holder = new ViewHolder(root);
                return holder;
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.tvContent.setText("item 内容:" + (position + 1));
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });

        // 使用自定义的ItemDecoration
        mRecyclerView.addItemDecoration(new ItemDecoration());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }

    class ItemDecoration extends RecyclerView.ItemDecoration {

        private int mDecHeight = 100;

        // 画矩形背景
        private Paint mPaint = new Paint();

        // 画悬浮标签的文字
        private TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        // 悬浮标签的大小
        private Rect mRect = new Rect();

        public ItemDecoration() {
            mPaint.setColor(0xffe62117);
            mTextPaint.setColor(0xffffffff);
            mTextPaint.setTextSize(40);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = mDecHeight;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

            int left = parent.getPaddingLeft();
            int right = parent.getRight() - parent.getPaddingRight();

            int count = parent.getChildCount();
            int paddingTop = parent.getPaddingTop();
            for (int i = 0; i < count; i++) {
                View child = parent.getChildAt(i);
                int bottom = child.getTop();
                int top = bottom - mDecHeight;

                Log.i(TAG,
                        "onDraw: childTop=" + bottom + ";top=" + top + ";paddingTop=" + paddingTop);
                // 标题距离父容器顶部的距离小于父容器的paddingTop，则标题顶部保持不变
                if (top - paddingTop <= 0) {
                    top = paddingTop;
                }

                // 如果标题底部距离父容器的距离小于父容器的paddingTop，则底部距离保持不变
                if (bottom - paddingTop <= 0) {
                    bottom = paddingTop;
                }

                // 画矩形背景
                mRect.set(left, top, right, bottom);
                c.drawRect(mRect, mPaint);

                // 画文案
                int index = parent.getChildAdapterPosition(child);
                String tag = getFlowText(index);
                mTextPaint.getTextBounds(tag, 0, tag.length(), mRect);

                c.drawText(tag,
                        left + mTextPaint.getTextSize(),
                        bottom - mDecHeight / 2 + mRect.height() / 2,
                        mTextPaint);
            }
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            int left = parent.getPaddingLeft();
            int right = parent.getRight() - parent.getPaddingRight();

            LinearLayoutManager layoutManager = null;
            if (parent.getLayoutManager() instanceof LinearLayoutManager) {
                layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            }
            int position = layoutManager.findFirstVisibleItemPosition();
            View firstChild = layoutManager.findViewByPosition(position);

            int paddingTop = parent.getPaddingTop();
            int top = paddingTop;
            int bottom = top + mDecHeight;
            bottom = Math.min(bottom, firstChild.getBottom());
            // 画矩形背景
            mRect.set(left, top, right, bottom);
            c.drawRect(mRect, mPaint);

            // 画文案
            String tag = getFlowText(position);
            mTextPaint.getTextBounds(tag, 0, tag.length(), mRect);

            c.drawText(tag, left + mTextPaint.getTextSize(),
                    bottom - mDecHeight / 2 + mRect.height() / 2, mTextPaint);

        }

        private String getFlowText(int position) {
            return "悬浮标签:" + (position + 1);
        }


    }
}
