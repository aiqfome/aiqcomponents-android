package com.aiqfome.aiqcomponents.selector

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.aiqfome.aiqcomponents.R
import com.aiqfome.aiqcomponents.databinding.SelectorBinding

class Selector @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
    private var _binding: SelectorBinding = SelectorBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    val binding: SelectorBinding get() = _binding

    private var controller: SelectorController<*>? = null

    init {
        attrs?.let { attrsSet ->
            val typedArray = context.obtainStyledAttributes(
                attrsSet,
                R.styleable.Selector,
                defStyleAttr,
                defStyleRes
            )

            typedArray.getColor(
                R.styleable.Selector_selectorBackgroundColor,
                ContextCompat.getColor(context, R.color.colorBackground),
            ).run {
                binding.inputLayout.setBackgroundColor(this)
            }

            typedArray.getResourceId(
                R.styleable.Selector_selectorTextAppearance,
                -1
            ).let {
                if (it > 0) {
                    TextViewCompat.setTextAppearance(binding.etInput, it)
                }
            }

            typedArray.getString(R.styleable.Selector_selectorTitle)?.let {
                binding.inputLayout.hint = it
            }

            typedArray.recycle()
        }

        binding.etInput.keyListener = null

        val icChevronDown: Drawable? = VectorDrawableCompat.create(
            context.resources,
            R.drawable.ic_chevron_down,
            context.theme
        )
        binding.etInput.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            icChevronDown,
            null
        )

        setOnClickListener(onClickListener())

        binding.root.setOnClickListener(onClickListener())

        binding.inputLayout.setOnClickListener(onClickListener())
        binding.etInput.setOnClickListener(onClickListener())
    }

    private fun onClickListener(): OnClickListener {
        return OnClickListener {
            controller?.show() ?: run {
                Log.e(TAG, "no controller found, please setup Selector Component!")
            }
        }
    }

    fun setup(controller: SelectorController<*>) {
        controller.setSelector(this)
        this.controller = controller
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        super.setClickable(enabled)

        binding.inputLayout.isEnabled = enabled
        binding.inputLayout.isClickable = enabled
        binding.etInput.isEnabled = enabled
        binding.etInput.isClickable = enabled

        if (!enabled) {
            val color = ContextCompat.getDrawable(context, R.drawable.rounded_backgroun_disable)
            binding.etInput.background = color
            binding.inputLayout.alpha = 0.6f
        } else binding.inputLayout.alpha = 1f
    }

    fun setSelectedItem(itemText: String?) {
        binding.etInput.setText(itemText)
    }

    companion object {
        private val TAG = Selector::class.java.simpleName
    }
}