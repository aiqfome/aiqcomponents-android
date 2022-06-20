package com.aiqfome.aiqcomponents.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aiqfome.aiqcomponents.R
import com.aiqfome.aiqcomponents.databinding.LayoutBottomSheetListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListBottomSheet(
    private val title: String,
    private val adapter: RecyclerView.Adapter<*>,
) : BottomSheetDialogFragment() {

    private var _binding: LayoutBottomSheetListBinding? = null
    private val binding: LayoutBottomSheetListBinding get() = _binding!!

    init {
        setStyle(STYLE_NORMAL, R.style.aiq_BottomSheet)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutBottomSheetListBinding.inflate(inflater, container, false)

        binding.title.text = this.title

        binding.rvItems.apply {
            adapter = this@ListBottomSheet.adapter
            isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        return binding.root
    }
}