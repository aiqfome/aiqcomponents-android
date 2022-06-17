package com.aiqfome.aiqcomponents.selector

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentManager
import com.aiqfome.aiqcomponents.adapters.BaseAdapter
import com.aiqfome.aiqcomponents.adapters.CommonAdapter
import com.aiqfome.aiqcomponents.adapters.CommonSearchableAdapter
import com.aiqfome.aiqcomponents.adapters.OnItemClickListener
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.sheets.ListBottomSheet
import com.aiqfome.aiqcomponents.sheets.SearchableListBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class SelectorController<T> @JvmOverloads constructor(
    private val fragmentManager: FragmentManager,
    private val title: String,
    private val list: List<Item<T>>,
    private val isSearchable: Boolean = false,
    private val shouldDismissOnSelect: Boolean = true,
    private val isFirstItemPreSelected: Boolean = false,
    private val displaySelectedSubtitle: Boolean = false,
) {
    private var selector: Selector? = null

    private lateinit var adapter: BaseAdapter<T>

    private lateinit var bottomSheet: BottomSheetDialogFragment

    init {
        setup()
    }

    private fun setup() {
        if (isSearchable) {
            adapter = CommonSearchableAdapter()
            bottomSheet = (adapter as? CommonSearchableAdapter)?.let {
                SearchableListBottomSheet(title, it)
            }!!
        } else {
            adapter = CommonAdapter()
            bottomSheet = (adapter as? CommonAdapter)?.let {
                ListBottomSheet(title, it)
            }!!
        }

        adapter.onItemClickListener = OnItemClickListener { item ->
            selectItem(item)
            if (shouldDismissOnSelect) dismiss()
        }

        adapter.submitList(list)
    }

    private fun selectItem(item: Item<T>) {
        selector?.setSelectedItem(
            if (displaySelectedSubtitle) {
                item.subTitle
            } else item.title
        )

        onItemSelected(item.item)
    }

    abstract fun onItemSelected(item: T)

    fun setSelector(selector: Selector?) {
        this.selector = selector

        if (isFirstItemPreSelected && list.isNotEmpty()) {
            selectItem(list[0])
        }
    }

    fun show() {
        if (!bottomSheet.isAdded) {
            bottomSheet.show(
                fragmentManager,
                "$title${if (isSearchable) "searchableListBottomSheet" else "listBottomSheet"}"
            )
        }
    }

    private fun dismiss() {
        Handler(Looper.getMainLooper()).postDelayed({
            bottomSheet.dismissAllowingStateLoss()
        }, DELAY_MILLIS)
    }

    companion object {
        private const val DELAY_MILLIS = 200L
    }
}