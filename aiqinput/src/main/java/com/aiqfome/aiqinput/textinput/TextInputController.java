package com.aiqfome.aiqinput.textinput;

import android.os.Handler;
import android.view.View;

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

    private Type selectedItem;

    private IconSubTitleAdapter adapter;
    private ListBottomSheet listBottomSheet;

    public TextInputController(FragmentManager fragmentManager,
                               String title,
                               List<IconSubTitleItem<Type>> itemList) {

        this.fragmentManager = fragmentManager;
        this.title = title;
        this.itemList = itemList;

        adapter = new IconSubTitleAdapter<Type>(itemList) {
            @Override
            public View.OnClickListener itemClickListener(IconSubTitleItem<Type> item) {
                return v -> {
                    if (textInput != null) textInput.setSelectedItem(item.getIcon());
                    selectedItem = item.getObject();

                    onItemSelected(item.getObject());
                };
            }
        };

        listBottomSheet = new ListBottomSheet(title, adapter);
    }

    public void setTextInput(TextInput textInput) {
        this.textInput = textInput;
    }

    public abstract void onItemSelected(Type object);

    public void show() {
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
