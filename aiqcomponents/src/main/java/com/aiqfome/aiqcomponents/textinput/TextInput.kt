package com.aiqfome.aiqcomponents.textinput

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputType
import android.text.TextUtils
import android.text.method.DigitsKeyListener
import android.text.method.KeyListener
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo.IME_NULL
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.aiqfome.aiqcomponents.R
import com.aiqfome.aiqcomponents.databinding.TextInputBinding

class TextInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var _binding: TextInputBinding = TextInputBinding.inflate(
        LayoutInflater.from(context),
        this,
    )
    val binding: TextInputBinding get() = _binding

    private var controller: TextInputController<*>? = null

    private val inputKeyListener: KeyListener = binding.etInput.keyListener
    private var inputType: Int

    init {
        inputType = inputKeyListener.inputType

        setupAttrs(
            context,
            attrs,
            defStyleAttr,
            defStyleRes,
        )

        setOnClickListener(onClickListener())

        binding.etInput.onFocusChangeListener =
            OnFocusChangeListener { _: View?, hasFocus: Boolean ->
                if (!hasFocus) {
                    binding.etInput.setSelection(0)
                } else {
                    binding.etInput.setSelection(binding.etInput.text!!.length)
                }
            }
    }


    private fun setupAttrs(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) {
        attrs?.let { attrsSet ->
            val typedArray = context.obtainStyledAttributes(
                attrsSet,
                R.styleable.TextInput,
                defStyleAttr,
                defStyleRes,
            )

            typedArray.getColor(
                R.styleable.TextInput_TextInputSelectorbackgroundColor,
                ContextCompat.getColor(context, R.color.colorBackground),
            ).run {
                binding.selector.setCardBackgroundColor(this)
            }

            typedArray.getColor(
                R.styleable.TextInput_TextInputbackgroundColor,
                ContextCompat.getColor(context, R.color.colorBackground),
            ).run {
                binding.input.setBackgroundColor(this)
            }

            typedArray.getResourceId(R.styleable.TextInput_textAppearance, -1).run {
                if (this > 0) {
                    TextViewCompat.setTextAppearance(binding.etInput, this)
                }
            }

            typedArray.getResourceId(R.styleable.TextInput_selectorDefaultIcon, -1).run {
                if (this > -1) {
                    val defaultIcon = AppCompatResources.getDrawable(getContext(), this)
                    binding.ivSelectedIcon.setImageDrawable(defaultIcon)
                }
            }

            typedArray.getString(R.styleable.TextInput_android_digits)?.let {
                binding.etInput.keyListener = DigitsKeyListener.getInstance(it)
            }

            typedArray.getInt(
                R.styleable.TextInput_android_inputType,
                InputType.TYPE_CLASS_TEXT,
            ).run {
                binding.etInput.inputType = this
                this@TextInput.inputType = this
            }

            typedArray.getBoolean(
                R.styleable.TextInput_android_selectAllOnFocus,
                false,
            ).run {
                binding.etInput.setSelectAllOnFocus(this)
            }

            typedArray.getString(R.styleable.TextInput_android_hint)?.let {
                binding.input.hint = it
            }

            typedArray.getInt(R.styleable.TextInput_android_imeOptions, IME_NULL).run {
                binding.etInput.imeOptions = this
            }

            typedArray.getString(R.styleable.TextInput_android_text)?.let {
                binding.etInput.setText(it)
            }

            typedArray.getDimension(R.styleable.TextInput_textSize, 0f).run {
                if (this > 0) {
                    binding.etInput.setTextSize(TypedValue.COMPLEX_UNIT_PX, this)
                }
            }

            typedArray.recycle()
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        binding.etInput.isEnabled = enabled
        setEllipsized(!enabled)
    }

    @Suppress("unused")
    fun setSelectorEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
    }

    @Suppress("unused")
    fun setInputType(inputType: Int) {
        this.inputType = inputType

        binding.etInput.inputType = inputType
    }

    private fun setEllipsized(enabled: Boolean) {
        binding.etInput.isCursorVisible = !enabled

        if (enabled) {
            binding.etInput.ellipsize = TextUtils.TruncateAt.END
            binding.etInput.keyListener = null

            return
        }

        binding.etInput.ellipsize = null
        binding.etInput.keyListener = inputKeyListener
        binding.etInput.inputType = inputType
    }

    private fun onClickListener(): OnClickListener {
        return OnClickListener {
            controller?.show() ?: run {
                Log.e(TAG, "no controller found, please setup TextInput Component!")
            }
        }
    }

    fun setup(controller: TextInputController<*>) {
        controller.setTextInput(this)

        this.controller = controller
    }

    fun setSelectedItem(icon: Drawable?) {
        binding.ivSelectedIcon.setImageDrawable(icon)
    }

    companion object {
        private val TAG = TextInput::class.java.simpleName
    }


}