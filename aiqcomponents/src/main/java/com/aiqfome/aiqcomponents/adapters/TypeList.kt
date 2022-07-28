package com.aiqfome.aiqcomponents.adapters

internal enum class TypeList(val id: Int) {
    TEXT(0),
    ICON(1);

    companion object {
        fun fromId(value: Int) = values().firstOrNull { it.id == value }
    }
}