package com.aiqfome.aiqcomponents.adapters.view_holders

import android.view.View
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.databinding.ItemlistIconSubTitleBinding

internal class IconItemViewHolder(
    val binding: ItemlistIconSubTitleBinding,
) : BaseViewHolder(binding.root) {
    override fun bind(item: Item<*>) {
        (item as? Item.Icon)?.let {
            binding.viewObject = item

            binding.tvSubtitle.visibility = if (item.subTitle.isNullOrEmpty()) {
                View.INVISIBLE
            } else View.VISIBLE
            binding.ivIcon.setImageDrawable(item.icon)
            binding.executePendingBindings()
        }
    }
}