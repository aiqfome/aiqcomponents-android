package com.aiqfome.aiqcomponents.adapters.view_holders

import androidx.core.view.isGone
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.databinding.ItemlistSubTitleBinding

internal class TextItemViewHolder(
    val binding: ItemlistSubTitleBinding,
) : BaseViewHolder(binding.root) {
    override fun bind(item: Item<*>) {
        (item as? Item.Text)?.let {
            binding.viewObject = item

            binding.tvSubtitle.isGone = item.subTitle.isNullOrEmpty()

            binding.executePendingBindings()
        }
    }
}