package com.aiqfome.aiqcomponents.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aiqfome.aiqcomponents.adapters.model.Item
import com.aiqfome.aiqcomponents.adapters.view_holders.BaseViewHolder
import com.aiqfome.aiqcomponents.adapters.view_holders.IconItemViewHolder
import com.aiqfome.aiqcomponents.adapters.view_holders.TextItemViewHolder
import com.aiqfome.aiqcomponents.databinding.ItemlistIconSubTitleBinding
import com.aiqfome.aiqcomponents.databinding.ItemlistSubTitleBinding

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClickListener: (OnItemClickListener<T>)? = null

    private val diffCallback = object : DiffUtil.ItemCallback<Item<T>>() {
        override fun areItemsTheSame(
            oldItem: Item<T>,
            incomingItem: Item<T>
        ): Boolean {
            return oldItem.hashCode() == incomingItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: Item<T>,
            incomingItem: Item<T>
        ): Boolean {
            return oldItem.hashCode() == incomingItem.hashCode()
        }
    }

    internal val differ by lazy { AsyncListDiffer(this, diffCallback) }

    @SuppressLint("NotifyDataSetChanged")
    open fun submitList(list: List<Item<T>>) {
        differ.submitList(list)
        // We must redraw our list in order to apply UI rules on binding the object.
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position]) {
            is Item.Text<*> -> TypeList.TEXT.id
            is Item.Icon<*> -> TypeList.ICON.id
            else -> throw ClassCastException("Unknown list type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(TypeList.fromId(viewType)) {
            TypeList.TEXT -> TextItemViewHolder(ItemlistSubTitleBinding.inflate(inflater))
            TypeList.ICON -> IconItemViewHolder(ItemlistIconSubTitleBinding.inflate(inflater))
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item: Item<T> = differ.currentList[position]
        (viewHolder as? BaseViewHolder)?.let { holder ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItem(item)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}