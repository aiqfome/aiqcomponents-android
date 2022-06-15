package com.aiqfome.aiqcomponents.adapters.view_holders

import android.view.View
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.databinding.ItemlistSubTitleBinding

internal class TextItemViewHolder(
    val binding: ItemlistSubTitleBinding,
) : BaseViewHolder(binding.root) {
    override fun bind(item: Item<*>) {
        (item as? Item.Text)?.let {
            binding.viewObject = item

            binding.tvSubtitle.visibility = if (item.subTitle.isNullOrEmpty()) {
                View.GONE
            } else View.VISIBLE
            binding.executePendingBindings()
        }
    }
}