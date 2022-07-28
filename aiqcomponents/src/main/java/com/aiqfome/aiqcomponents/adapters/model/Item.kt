package com.aiqfome.aiqcomponents.adapters.model

import android.graphics.drawable.Drawable

sealed class Item<T> @JvmOverloads constructor(
    open val item: T,
    open val title: String,
    open val icon: Drawable? = null
) {
    open val subTitle: String? = null
        get() = field ?: ""

    data class Text<T> @JvmOverloads constructor(
        override val item: T,
        override val title: String,
        override val subTitle: String? = null
    ) : Item<T>(item, title)

    data class Icon<T> @JvmOverloads constructor(
        override val item: T,
        override val title: String,
        override val subTitle: String? = null,
        override val icon: Drawable? = null,
    ) : Item<T>(item, title, icon)
}