package com.aiqfome.aiqinput.selector;

import android.os.Handler;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.aiqfome.aiqinput.ListBottomSheet;
import com.aiqfome.aiqinput.adapters.SubTitleAdapter;
import com.aiqfome.aiqinput.adapters.SubTitleItem;

import java.util.List;

public abstract class SelectorController<Type> {

    private Selector selector;

    private FragmentManager fragmentManager;
    private String title;
    private List<SubTitleItem<Type>> itemList;

    private boolean shouldDismissOnSelect = true;
    private boolean isFirstItemPreSelected = false;
    private boolean displaySelectedSubtitle = false;

    private Type selectedItem;

    private SubTitleAdapter adapter;
    private ListBottomSheet listBottomSheet;

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<SubTitleItem<Type>> itemList) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;

        setup();
    }

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<SubTitleItem<Type>> itemList,
                              boolean displaySelectedSubtitle) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.displaySelectedSubtitle = displaySelectedSubtitle;

        setup();
    }

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<SubTitleItem<Type>> itemList,
                              boolean shouldDismissOnSelect,
                              boolean isFirstItemPreSelected) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.shouldDismissOnSelect = shouldDismissOnSelect;
        this.isFirstItemPreSelected = isFirstItemPreSelected;

        setup();
    }

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<SubTitleItem<Type>> itemList,
                              boolean shouldDismissOnSelect,
                              boolean isFirstItemPreSelected,
                              boolean displaySelectedSubtitle) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.shouldDismissOnSelect = shouldDismissOnSelect;
        this.isFirstItemPreSelected = isFirstItemPreSelected;
        this.displaySelectedSubtitle = displaySelectedSubtitle;

        setup();
    }

    private void setup() {
        adapter = new SubTitleAdapter<Type>(itemList) {
            @Override
            public View.OnClickListener itemClickListener(SubTitleItem<Type> item) {
                return v -> {
                    selectItem(item);
                    if (shouldDismissOnSelect) dismiss();
                };
            }
        };

        listBottomSheet = new ListBottomSheet(title, adapter);
    }

    private void selectItem(SubTitleItem<Type> item) {
        if (selector != null)
            selector.setSelectedItem(displaySelectedSubtitle ? item.getSubTitle() : item.getTitle());

        selectedItem = item.getObject();

        onItemSelected(item.getObject());
    }

    public abstract void onItemSelected(Type object);

    void setSelector(Selector selector) {
        this.selector = selector;
        if (isFirstItemPreSelected && !itemList.isEmpty()) selectItem(itemList.get(0));
    }

    public void show() {
        if (!listBottomSheet.isAdded())
            listBottomSheet.show(fragmentManager, title + " listBottomSheet");
    }

    public void dismiss() {
        new Handler().postDelayed(() -> listBottomSheet.dismissAllowingStateLoss(), 200);
    }

    public List<SubTitleItem<Type>> getItemList() {
        return itemList;
    }

    public Type getSelectedItem() {
        return selectedItem;
    }

    public SubTitleAdapter getAdapter() {
        return adapter;
    }

    public ListBottomSheet getListBottomSheet() {
        return listBottomSheet;
    }
}
