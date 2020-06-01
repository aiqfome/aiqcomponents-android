package com.aiqfome.aiqinput.sheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiqfome.aiqinput.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ListBottomSheet extends BottomSheetDialogFragment {

    private String title;
    private RecyclerView.Adapter adapter;

    public ListBottomSheet(String title, RecyclerView.Adapter adapter) {
        this.title = title;
        this.adapter = adapter;

        this.setStyle(STYLE_NORMAL, R.style.aiq_BottomSheet);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_bottom_sheet_list, container, false);

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(this.title);

        RecyclerView rvItems = (RecyclerView) view.findViewById(R.id.rv_items);
        rvItems.setAdapter(adapter);
        rvItems.setNestedScrollingEnabled(true);
        rvItems.setLayoutManager(new LinearLayoutManager
                (getContext(), RecyclerView.VERTICAL, false));

        return view;
    }

}
