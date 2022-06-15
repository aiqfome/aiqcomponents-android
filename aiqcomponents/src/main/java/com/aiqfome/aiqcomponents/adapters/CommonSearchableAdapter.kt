package com.aiqfome.aiqcomponents.adapters

import android.annotation.SuppressLint
import android.widget.Filter
import android.widget.Filterable
import com.aiqfome.aiqcomponents.adapters.model.Item
import org.apache.commons.lang3.StringUtils

/**
 * Multi-purpose Adapter that can be used for both BaseItem and IconItem Lists!
 * And it implements  Filterable too?! :0
 *
 * @param <T> When using this class you need to specify the T of the Object of this adapter
 * @author Bruno Cesar, bcesar.g6@gmail.com
 * @since 14/05/2020
</T> */
internal class CommonSearchableAdapter<T> :
    BaseAdapter<T>(),
    Filterable {

    private var originalList: List<Item<T>>? = null

    private val filter = ItemFilter()

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<Item<T>>) {
        differ.submitList(list)

        if (originalList == null) {
            originalList = list
        }

        // We must redraw our list in order to apply UI rules on binding the object.
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getFilter(): Filter = filter

    private inner class ItemFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = mutableListOf<Item<T>>()

            for (item in originalList!!) {
                val filterableString = StringUtils.stripAccents(item.title)
                val filterString = StringUtils.stripAccents(constraint.toString().lowercase())

                when (item) {
                    is Item.Text -> {
                        val filterableSubtitleString = StringUtils.stripAccents(item.subTitle)

                        if (filterableString.lowercase().contains(filterString)
                            || filterableSubtitleString.lowercase().contains(filterString)
                        ) {
                            filteredList.add(item)
                        }
                    }
                    is Item.Icon -> {
                        if (filterableString.lowercase().contains(filterString)) {
                            filteredList.add(item)
                        }
                    }
                }
            }

            return FilterResults().apply {
                values = filteredList
                count = filteredList.size
            }
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            submitList((results.values as? List<Item<T>>) ?: differ.currentList)
        }
    }
}