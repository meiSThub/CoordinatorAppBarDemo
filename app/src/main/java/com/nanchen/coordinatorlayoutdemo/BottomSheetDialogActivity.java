package com.nanchen.coordinatorlayoutdemo;

import com.nanchen.coordinatorlayoutdemo.fragment.CusBottomSheetDialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author mxb
 * @date 2019/6/26
 * @desc
 * @desired
 */
public class BottomSheetDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog);
    }

    public void onClick(View view) {
        View root = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null, false);
        ListView listView = root.findViewById(R.id.recycler_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{
                "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
                "A", "A",
                "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
        }));
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(root);
        dialog.show();
    }

    public void onClick1(View view) {
        CusBottomSheetDialogFragment fragment = new CusBottomSheetDialogFragment();
        fragment.show(getSupportFragmentManager(), "dialog");
    }
}
