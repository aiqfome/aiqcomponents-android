package com.aiqcomponents.sample.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.aiqcomponents.sample.R
import com.aiqcomponents.sample.databinding.FragmentLoadingButtonsBinding
import com.aiqfome.aiqcomponents.progressbutton.DrawableButton
import com.aiqfome.aiqcomponents.progressbutton.ProgressButtonUtils.Companion.hideProgress
import com.aiqfome.aiqcomponents.progressbutton.ProgressParams
import com.aiqfome.aiqcomponents.progressbutton.showProgress

class LoadingButtonsFragment : Fragment(R.layout.fragment_loading_buttons) {

    private var _binding: FragmentLoadingButtonsBinding? = null
    private val binding: FragmentLoadingButtonsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLoadingButtonsBinding.bind(view)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.btnProgressLeft.setOnClickListener {
            showProgressLeft(binding.btnProgressLeft)
        }
        binding.btnProgressCenter.setOnClickListener {
            showProgressCenter(binding.btnProgressCenter)
        }
        binding.btnProgressRight.setOnClickListener {
            showProgressRight(binding.btnProgressRight)
        }
        binding.btnProgressCustom.setOnClickListener {
            showProgressCustom(binding.btnProgressCustom)
        }
    }

    private fun showProgressLeft(button: Button) {
        button.showProgress {
            this.apply {
                buttonTextRes = R.string.loading
                progressColor = Color.WHITE
                gravity = DrawableButton.GRAVITY_TEXT_START
            }
            button.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                button.isEnabled = true
                hideProgress(button, R.string.progress_left_text)
            }, 3000)
        }
    }

    private fun showProgressCenter(button: Button) {
        button.showProgress {
            this.apply {
                progressColor = Color.WHITE
                gravity = DrawableButton.GRAVITY_CENTER
            }
            button.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                button.isEnabled = true
                hideProgress(button, R.string.progress_center_text)
            }, 3000)
        }
    }

    private fun showProgressRight(button: Button) {
        button.showProgress {
            this.apply {
                buttonTextRes = R.string.loading
                progressColor = Color.WHITE
            }
            button.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                button.isEnabled = true
                hideProgress(button, R.string.progress_right_text)
            }, 3000)
        }
    }

    private fun showProgressCustom(button: Button) {
        button.showProgress {
            this.apply {
                buttonTextRes = R.string.loading
                progressColors = intArrayOf(Color.WHITE, Color.MAGENTA, Color.GREEN)
                gravity = DrawableButton.GRAVITY_TEXT_END
                progressRadiusRes = R.dimen.progressRadius
                progressStrokeRes = R.dimen.progressStroke
                textMarginRes = R.dimen.textMarginStyled
            }
            button.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                button.isEnabled = true
                hideProgress(button, R.string.progress_custom_text)
            }, 3000)
        }
    }

}