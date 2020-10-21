package com.aiqcomponents.sample.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.aiqcomponents.sample.R
import com.aiqfome.aiqcomponents.progressbutton.DrawableButton
import com.aiqfome.aiqcomponents.progressbutton.ProgressButtonUtils.Companion.hideProgress
import com.aiqfome.aiqcomponents.progressbutton.ProgressParams
import com.aiqfome.aiqcomponents.progressbutton.showProgress
import kotlinx.android.synthetic.main.fragment_loading_buttons.*
import kotlinx.android.synthetic.main.fragment_loading_buttons.view.*

class LoadingButtonsFragment : Fragment(R.layout.fragment_loading_buttons) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        layoutLoadingButtonsRoot.btnProgressLeft?.setOnClickListener {
            showProgressLeft(layoutLoadingButtonsRoot.btnProgressLeft)
        }
        layoutLoadingButtonsRoot.btnProgressCenter?.setOnClickListener {
            showProgressCenter(layoutLoadingButtonsRoot.btnProgressCenter)
        }
        layoutLoadingButtonsRoot.btnProgressRight?.setOnClickListener {
            showProgressRight(layoutLoadingButtonsRoot.btnProgressRight)
        }
        layoutLoadingButtonsRoot.btnProgressCustom?.setOnClickListener {
            showProgressCustom(layoutLoadingButtonsRoot.btnProgressCustom)
        }
    }

    private fun showProgressLeft(button: Button) {
        button.showProgress {
            ProgressParams().apply {
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
            ProgressParams().apply {
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
            ProgressParams().apply {
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
            ProgressParams().apply {
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