package com.aiqfome.aiqcomponents.sheets;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiqfome.aiqcomponents.R;
import com.aiqfome.aiqcomponents.adapters.TextListSearchableAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SearchableListBottomSheet extends BottomSheetDialogFragment {

    private String title;
    private TextListSearchableAdapter adapter;
    private BottomSheetDialog dialog;
    private SearchView svSearch;

    public SearchableListBottomSheet(String title, TextListSearchableAdapter adapter) {
        this.title = title;
        this.adapter = adapter;

        this.setStyle(STYLE_NORMAL, R.style.aiq_BottomSheet);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_bottom_sheet_searchable_list,
                container, false);

        TextView title = view.findViewById(R.id.title);
        title.setText(this.title);

        RecyclerView rvItems = view.findViewById(R.id.rv_items);
        rvItems.setAdapter(adapter);
        rvItems.setNestedScrollingEnabled(true);

        rvItems.setLayoutManager(
                new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL,
                false)
        );

        SearchView svSearch = this.svSearch = view.findViewById(R.id.sv_search);

        // When clicked, remove the iconified property to make editable.
        svSearch.setOnClickListener(view1 -> svSearch.setIconified(false));

        // When input get out of focus, add the iconified property to respect UI behavior.
        svSearch.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus)
                svSearch.setIconified(true);
        });

        svSearch.setOnSearchClickListener(v -> {
            BottomSheetDialog d = dialog;

            FrameLayout bottomSheet = d.findViewById
                    (com.google.android.material.R.id.design_bottom_sheet);

            if (bottomSheet != null)
                BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
        });

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }


    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        Context context = getContext();

        if (context != null) {
            InputMethodManager imm = (InputMethodManager)
                    context.getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm != null && this.getView() != null)
                imm.hideSoftInputFromWindow
                        (this.getView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        svSearch.setQuery("", true);

        super.onDismiss(dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        return dialog;
    }

}
