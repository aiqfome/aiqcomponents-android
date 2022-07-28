package com.aiqfome.aiqcomponents.selector

import androidx.fragment.app.FragmentManager
import com.aiqfome.aiqcomponents.adapters.TextListAdapter
import com.aiqfome.aiqcomponents.adapters.TextListSearchableAdapter
import com.aiqfome.aiqcomponents.adapters.OnItemClickListener
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.controller.BaseController
import com.aiqfome.aiqcomponents.sheets.ListBottomSheet
import com.aiqfome.aiqcomponents.sheets.SearchableListBottomSheet

abstract class SelectorController<T> @JvmOverloads constructor(
    private val fragmentManager: FragmentManager,
    private val title: String,
    private val list: List<Item<T>>,
    private val isSearchable: Boolean = false,
    private val shouldDismissOnSelect: Boolean = true,
    private val isFirstItemPreSelected: Boolean = false,
    private val displaySelectedSubtitle: Boolean = false,
) : BaseController<T>() {
    private var selector: Selector? = null

    init {
        setup()
    }

    final override fun setup() {
        if (isSearchable) {
            adapter = TextListSearchableAdapter()
            bottomSheet = (adapter as? TextListSearchableAdapter)?.let {
                SearchableListBottomSheet(title, it)
            }!!
        } else {
            adapter = TextListAdapter()
            bottomSheet = (adapter as? TextListAdapter)?.let {
                ListBottomSheet(title, it)
            }!!
        }

        adapter.onItemClickListener = OnItemClickListener { item ->
            selectItem(item)
            if (shouldDismissOnSelect) dismiss()
        }

        adapter.submitList(list)
    }

    override fun selectItem(item: Item<T>) {
        selector?.setSelectedItem(
            if (displaySelectedSubtitle) {
                item.subTitle
            } else item.title
        )

        onItemSelected(item.item)
    }

    fun setSelector(selector: Selector?) {
        this.selector = selector

        if (isFirstItemPreSelected && list.isNotEmpty()) {
            selectItem(list[FIRST_ITEM])
        }
    }

    override fun show() {
        if (!bottomSheet.isAdded) {
            val tag = title + if (isSearchable) {
                SEARCHABLE_LIST_BOTTOM_SHEET
            } else LIST_BOTTOM_SHEET
            bottomSheet.show(
                fragmentManager,
                tag
            )
        }
    }
}