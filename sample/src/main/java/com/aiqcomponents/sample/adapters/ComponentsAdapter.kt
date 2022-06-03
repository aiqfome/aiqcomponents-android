package com.aiqcomponents.sample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aiqcomponents.sample.databinding.ComponentListItemBinding
import com.aiqcomponents.sample.models.ComponentItem

class ComponentsAdapter(private val componentsDataset: List<ComponentItem>) :
    RecyclerView.Adapter<ComponentsAdapter.ComponentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val itemBinding = ComponentListItemBinding.inflate(inflater, parent, false)

        return ComponentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        val componentItem = componentsDataset[position]
        holder.bind(componentItem)
    }

    override fun getItemCount() = componentsDataset.size

    inner class ComponentViewHolder(
        private val binding: ComponentListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(componentItem: ComponentItem) {
            binding.tvName.text = componentItem.name
            binding.tvDescription.text = componentItem.description

            itemView.rootView?.setOnClickListener(componentItem.clickListener)
        }
    }
}