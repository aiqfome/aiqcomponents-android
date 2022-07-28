package com.aiqfome.aiqcomponents.adapters.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aiqfome.aiqcomponents.adapters.model.Item

internal abstract class  BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Item<*>)
}