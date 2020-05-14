package com.aiqfome.aiqinput.textinput;

import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.aiqfome.aiqinput.adapters.CommonAdapter;
import com.aiqfome.aiqinput.adapters.BaseItem;
import com.aiqfome.aiqinput.adapters.IconItem;
import com.aiqfome.aiqinput.sheets.ListBottomSheet;

import java.util.List;

public abstract class TextInputController<Type> {

    private TextInput textInput;

    private FragmentManager fragmentManager;
    private String title;
    private List<IconItem<Type>> itemList;
    private boolean shouldDismissOnSelect = true;
    private boolean isFirstItemPreSelected = false;

    private Type selectedItem;

    private CommonAdapter adapter;
    private ListBottomSheet listBottomSheet;

    public TextInputController(@NonNull FragmentManager fragmentManager,
                               String title,
                               @NonNull List<IconItem<Type>> itemList,
                               boolean shouldDismissOnSelect,
                               boolean isFirstItemPreSelected) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.shouldDismissOnSelect = shouldDismissOnSelect;
        this.isFirstItemPreSelected = isFirstItemPreSelected;

        setup();
    }

    public TextInputController(@NonNull FragmentManager fragmentManager,
                               String title,
                               @NonNull List<IconItem<Type>> itemList,
                               boolean shouldDismissOnSelect) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.shouldDismissOnSelect= shouldDismissOnSelect;

        setup();
    }

    public TextInputController(@NonNull FragmentManager fragmentManager,
                               String title,
                               @NonNull List<IconItem<Type>> itemList) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;

        setup();
    }

    //Icon Setup
    private void setup() {
        adapter = new CommonAdapter<Type>() {
            @Override
            public View.OnClickListener itemClickListener(BaseItem<Type> item) {
                return v -> {
                    selectItem((IconItem<Type>) item);
                    if (shouldDismissOnSelect) dismiss();
                };
            }
        };

        adapter.setIconItemList(itemList);

        listBottomSheet = new ListBottomSheet(title, adapter);
    }

    private void selectItem(IconItem<Type> item) {
        if (textInput != null) textInput.setSelectedItem(item.getIcon());
        selectedItem = item.getObject();

        onItemSelected(item.getObject());
    }

    public abstract void onItemSelected(Type object);

    void setTextInput(TextInput textInput) {
        this.textInput = textInput;
        if (isFirstItemPreSelected && !itemList.isEmpty()) selectItem(itemList.get(0));

    }

    public void show() {
        if (!listBottomSheet.isAdded())
            listBottomSheet.show(fragmentManager, title + " listBottomSheet");
    }

    public void dismiss() {
        new Handler().postDelayed(() -> listBottomSheet.dismissAllowingStateLoss(), 200);
    }

    public Type getSelectedItem() {
        return selectedItem;
    }

    public List<IconItem<Type>> getItemList() {
        return itemList;
    }

    public CommonAdapter getAdapter() {
        return adapter;
    }

    public ListBottomSheet getListBottomSheet() {
        return listBottomSheet;
    }
}
