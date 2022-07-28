package com.aiqcomponents.sample.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiqcomponents.sample.R
import com.aiqcomponents.sample.adapters.ComponentsAdapter
import com.aiqcomponents.sample.databinding.FragmentHomeBinding
import com.aiqcomponents.sample.models.ComponentItems

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)
        setUpComponentsRecyclerView()
    }

    private fun setUpComponentsRecyclerView() {
        val componentsAdapter = ComponentsAdapter(ComponentItems.list)
        binding.componentsRecyclerView.apply {
            adapter = componentsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}