package com.aiqfome.aiqinput.textinput;

import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.aiqfome.aiqinput.IconSubTitleAdapter;
import com.aiqfome.aiqinput.IconSubTitleItem;
import com.aiqfome.aiqinput.ListBottomSheet;

import java.util.List;

public abstract class TextInputController<Type> {

    private TextInput textInput;

    private FragmentManager fragmentManager;
    private String title;
    private List<IconSubTitleItem<Type>> itemList;
    private boolean shouldDismissOnSelect = true;
    private boolean isFirstItemPreSelected = false;

    private Type selectedItem;

    private IconSubTitleAdapter adapter;
    private ListBottomSheet listBottomSheet;

    public TextInputController(@NonNull FragmentManager fragmentManager,
                               String title,
                               @NonNull List<IconSubTitleItem<Type>> itemList,
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
                               @NonNull List<IconSubTitleItem<Type>> itemList,
                               boolean shouldDismissOnSelect) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;
        this.shouldDismissOnSelect= shouldDismissOnSelect;

        setup();
    }

    public TextInputController(@NonNull FragmentManager fragmentManager,
                               String title,
                               @NonNull List<IconSubTitleItem<Type>> itemList) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;

        setup();
    }

    private void setup() {
        adapter = new IconSubTitleAdapter<Type>(itemList) {
            @Override
            public View.OnClickListener itemClickListener(IconSubTitleItem<Type> item) {
                return v -> {
                    selectItem(item);
                    if (shouldDismissOnSelect) dismiss();
                };
            }
        };

        listBottomSheet = new ListBottomSheet(title, adapter);
    }

    private void selectItem(IconSubTitleItem<Type> item) {
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

    public List<IconSubTitleItem<Type>> getItemList() {
        return itemList;
    }

    public IconSubTitleAdapter getAdapter() {
        return adapter;
    }

    public ListBottomSheet getListBottomSheet() {
        return listBottomSheet;
    }
}
