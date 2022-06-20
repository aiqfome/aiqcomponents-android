package com.aiqfome.aiqcomponents.sheets

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aiqfome.aiqcomponents.R
import com.aiqfome.aiqcomponents.adapters.TextListSearchableAdapter
import com.aiqfome.aiqcomponents.databinding.LayoutBottomSheetSearchableListBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchableListBottomSheet(
    private val title: String,
    private val adapter: TextListSearchableAdapter<*>,
) : BottomSheetDialogFragment() {
    private var _binding: LayoutBottomSheetSearchableListBinding? = null
    private val binding: LayoutBottomSheetSearchableListBinding get() = _binding!!

    private var dialog: BottomSheetDialog? = null

    init {
        setStyle(STYLE_NORMAL, R.style.aiq_BottomSheet)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            LayoutBottomSheetSearchableListBinding.inflate(inflater, container, false)

        binding.title.text = this.title

        binding.rvItems.apply {
            adapter = this@SearchableListBottomSheet.adapter
            isNestedScrollingEnabled = true
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        binding.svSearch.apply {
            setOnClickListener {
                binding.svSearch.isIconified = false
            }

            setOnQueryTextFocusChangeListener { _, hasFocus ->
                if (!hasFocus) binding.svSearch.isIconified = true
            }

            setOnSearchClickListener {
                dialog!!.findViewById<FrameLayout>(
                    com.google.android.material.R.id.design_bottom_sheet
                )?.let {
                    BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
                }
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    adapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            })
        }

        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        context?.let {
            val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(
                binding.root.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

        binding.svSearch.setQuery("", true)

        super.onDismiss(dialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        return dialog!!
    }

}