package com.aiqfome.aiqcomponents.adapters

import com.aiqfome.aiqcomponents.adapters.model.Item

fun interface OnItemClickListener<T> {
    fun onItem(item: Item<T>)
}