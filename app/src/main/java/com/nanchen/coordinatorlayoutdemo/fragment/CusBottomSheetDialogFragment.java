package com.nanchen.coordinatorlayoutdemo.fragment;

import com.nanchen.coordinatorlayoutdemo.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author mxb
 * @date 2019/6/26
 * @desc
 * @desired
 */
public class CusBottomSheetDialogFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View root = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, container, false);
        ListView listView = root.findViewById(R.id.recycler_view);
        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, new String[]{
                "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
                "A", "A",
                "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A"
        }));

        return root;
    }
}
