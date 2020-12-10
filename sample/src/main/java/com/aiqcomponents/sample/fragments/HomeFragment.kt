package com.aiqcomponents.sample.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiqcomponents.sample.R
import com.aiqcomponents.sample.adapters.ComponentsAdapter
import com.aiqcomponents.sample.models.ComponentItems
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpComponentsRecyclerView()
    }

    private fun setUpComponentsRecyclerView() {
        val componentsAdapter = ComponentsAdapter(ComponentItems.list)
        layoutHomeRoot.componentsRecyclerView?.apply {
            adapter = componentsAdapter
            layoutManager =
                    LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            addItemDecoration(
                    DividerItemDecoration(
                            context,
                            DividerItemDecoration.VERTICAL
                    )
            )
        }
    }

}