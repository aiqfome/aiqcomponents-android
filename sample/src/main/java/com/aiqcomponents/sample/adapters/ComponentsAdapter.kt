package com.aiqcomponents.sample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aiqcomponents.sample.R
import com.aiqcomponents.sample.models.ComponentItem
import kotlinx.android.synthetic.main.component_list_item.view.*

class ComponentsAdapter(private val componentsDataset: List<ComponentItem>) :
        RecyclerView.Adapter<ComponentsAdapter.ComponentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ComponentViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        val componentItem = componentsDataset[position]
        holder.bind(componentItem)
    }

    override fun getItemCount() = componentsDataset.size

    inner class ComponentViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.component_list_item, parent, false)
            ) {

        private var tvName: TextView? = null
        private var tvDescription: TextView? = null


        init {
            tvName = itemView.componentItemLayout.tvName
            tvDescription = itemView.componentItemLayout.tvDescription
        }

        fun bind(componentItem: ComponentItem) {
            tvName?.text = componentItem.name
            tvDescription?.text = componentItem.description
            itemView.rootView?.setOnClickListener(componentItem.clickListener)
        }

    }

}