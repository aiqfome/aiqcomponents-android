package com.aiqfome.aiqcomponents.selector;

import android.os.Handler;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.aiqfome.aiqcomponents.adapters.BaseItem;
import com.aiqfome.aiqcomponents.adapters.CommonAdapter;
import com.aiqfome.aiqcomponents.adapters.CommonSearchableAdapter;
import com.aiqfome.aiqcomponents.sheets.ListBottomSheet;
import com.aiqfome.aiqcomponents.sheets.SearchableListBottomSheet;

import java.util.List;

public abstract class SelectorController<Type> {

    private Selector selector;

    private FragmentManager fragmentManager;
    private String title;
    private List<BaseItem<Type>> itemList;

    private boolean shouldDismissOnSelect = true;
    private boolean isFirstItemPreSelected = false;
    private boolean displaySelectedSubtitle = false;

    private Type selectedItem;

    private CommonAdapter adapter;
    private ListBottomSheet listBottomSheet;

    private CommonSearchableAdapter searchableAdapter;
    private SearchableListBottomSheet searchableListBottomSheet;

    private boolean isSearchable = false;

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<BaseItem<Type>> itemList) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;

        setup();
    }

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<BaseItem<Type>> itemList,
                              boolean isSearchable) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.isSearchable = isSearchable;

        setup();
    }

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<BaseItem<Type>> itemList,
                              boolean displaySelectedSubtitle,
                              boolean isSearchable) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.displaySelectedSubtitle = displaySelectedSubtitle;
        this.isSearchable = isSearchable;

        setup();
    }

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<BaseItem<Type>> itemList,
                              boolean shouldDismissOnSelect,
                              boolean isFirstItemPreSelected,
                              boolean isSearchable) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.shouldDismissOnSelect = shouldDismissOnSelect;
        this.isFirstItemPreSelected = isFirstItemPreSelected;
        this.isSearchable = isSearchable;

        setup();
    }

    public SelectorController(FragmentManager fragmentManager,
                              String title,
                              List<BaseItem<Type>> itemList,
                              boolean shouldDismissOnSelect,
                              boolean isFirstItemPreSelected,
                              boolean displaySelectedSubtitle,
                              boolean isSearchable) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.shouldDismissOnSelect = shouldDismissOnSelect;
        this.isFirstItemPreSelected = isFirstItemPreSelected;
        this.displaySelectedSubtitle = displaySelectedSubtitle;
        this.isSearchable = isSearchable;

        setup();
    }

    private void setup() {

        if (isSearchable) {

            searchableAdapter = new CommonSearchableAdapter<Type>() {
                @Override
                public View.OnClickListener itemClickListener(BaseItem<Type> item) {
                    return v -> {
                        selectItem(item);
                        if (shouldDismissOnSelect) dismiss();
                    };
                }
            };

            searchableAdapter.setBaseItemList(itemList);

            searchableListBottomSheet = new SearchableListBottomSheet(title, searchableAdapter);

        } else {

            adapter = new CommonAdapter<Type>() {
                @Override
                public View.OnClickListener itemClickListener(BaseItem<Type> item) {
                    return v -> {
                        selectItem(item);
                        if (shouldDismissOnSelect) dismiss();
                    };
                }
            };

            adapter.setBaseItemList(itemList);

            listBottomSheet = new ListBottomSheet(title, adapter);
        }

    }

    private void selectItem(BaseItem<Type> item) {
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

    void show() {
        if (isSearchable) {
            if (!searchableListBottomSheet.isAdded())
                searchableListBottomSheet.show(fragmentManager,
                        title + "searchableListBottomSheet");
        } else {
            if (!listBottomSheet.isAdded())
                listBottomSheet.show(fragmentManager, title + " listBottomSheet");
        }
    }

    public void dismiss() {
        if (isSearchable)
            new Handler().postDelayed(() -> searchableListBottomSheet.dismissAllowingStateLoss(),
                    200);
        else
            new Handler().postDelayed(() -> listBottomSheet.dismissAllowingStateLoss(),
                    200);
    }

    public List<BaseItem<Type>> getItemList() {
        return itemList;
    }

    public Type getSelectedItem() {
        return selectedItem;
    }

}
