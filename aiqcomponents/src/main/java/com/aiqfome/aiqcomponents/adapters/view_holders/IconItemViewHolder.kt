package com.aiqfome.aiqcomponents.adapters.view_holders

import androidx.core.view.isVisible
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.databinding.ItemlistIconSubTitleBinding

internal class IconItemViewHolder(
    val binding: ItemlistIconSubTitleBinding,
) : BaseViewHolder(binding.root) {
    override fun bind(item: Item<*>) {
        (item as? Item.Icon)?.let {
            binding.viewObject = item

            binding.tvSubtitle.isVisible = !item.subTitle.isNullOrEmpty()

            binding.ivIcon.setImageDrawable(item.icon)
            binding.executePendingBindings()
        }
    }
}