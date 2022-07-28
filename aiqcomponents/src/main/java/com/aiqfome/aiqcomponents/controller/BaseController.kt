package com.aiqfome.aiqcomponents.controller

import android.os.Handler
import android.os.Looper
import com.aiqfome.aiqcomponents.adapters.BaseAdapter
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseController<T> {
    internal lateinit var adapter: BaseAdapter<T>

    internal lateinit var bottomSheet: BottomSheetDialogFragment

    internal abstract fun setup()

    abstract fun onItemSelected(item: T)

    internal abstract fun selectItem(item: Item<T>)

    abstract fun show()

    internal fun dismiss() {
        Handler(Looper.getMainLooper()).postDelayed({
            bottomSheet.dismissAllowingStateLoss()
        }, DELAY_MILLIS)
    }

    companion object {
        internal const val DELAY_MILLIS = 200L
        internal const val FIRST_ITEM = 0

        internal const val LIST_BOTTOM_SHEET = "listBottomSheet"
        internal const val SEARCHABLE_LIST_BOTTOM_SHEET = "searchableListBottomSheet"
    }
}
