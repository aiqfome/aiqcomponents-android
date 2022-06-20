package com.aiqfome.aiqcomponents.textinput

import androidx.fragment.app.FragmentManager
import com.aiqfome.aiqcomponents.adapters.TextListAdapter
import com.aiqfome.aiqcomponents.adapters.OnItemClickListener
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.controller.BaseController
import com.aiqfome.aiqcomponents.sheets.ListBottomSheet

abstract class TextInputController<T> @JvmOverloads constructor(
    private val fragmentManager: FragmentManager,
    private val title: String,
    private val itemList: List<Item<T>>,
    private val shouldDismissOnSelect: Boolean = true,
    private val isFirstItemPreSelected: Boolean = false
) : BaseController<T>() {
    private var textInput: TextInput? = null

    init {
        setup()
    }

    //Icon Setup
    final override fun setup() {
        adapter = TextListAdapter()
        adapter.onItemClickListener = OnItemClickListener { item: Item<T> ->
            selectItem(item)
            if (shouldDismissOnSelect) dismiss()
        }

        adapter.submitList(itemList)

        bottomSheet = ListBottomSheet(title, adapter)
    }

     override fun selectItem(item: Item<T>) {
        textInput?.setSelectedItem(item.icon)

        onItemSelected(item.item)
    }

    fun setTextInput(textInput: TextInput?) {
        this.textInput = textInput

        if (isFirstItemPreSelected && itemList.isNotEmpty()) {
            selectItem(itemList[FIRST_ITEM])
        }
    }

    override fun show() {
        if (!bottomSheet.isAdded) {
            bottomSheet.show(
                fragmentManager,
                "$title $LIST_BOTTOM_SHEET"
            )
        }
    }
}