package com.aiqcomponents.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.aiqcomponents.sample.databinding.ActivityMainBinding;
import com.aiqcomponents.sample.models.Country;
import com.aiqcomponents.sample.models.Region;
import com.aiqfome.aiqcomponents.adapters.BaseItem;
import com.aiqfome.aiqcomponents.adapters.IconItem;
import com.aiqfome.aiqcomponents.progressbutton.DrawableButton;
import com.aiqfome.aiqcomponents.progressbutton.DrawableButtonExtensionsKt;
import com.aiqfome.aiqcomponents.selector.SelectorController;
import com.aiqfome.aiqcomponents.textinput.TextInputController;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.layoutButtonsSample.btnProgressLeft.setOnClickListener(v -> showProgressLeft(binding.layoutButtonsSample.btnProgressLeft));
        binding.layoutButtonsSample.btnProgressCenter.setOnClickListener(v -> showProgressCenter(binding.layoutButtonsSample.btnProgressCenter));
        binding.layoutButtonsSample.btnProgressRight.setOnClickListener(v -> showProgressRight(binding.layoutButtonsSample.btnProgressRight));
        binding.layoutButtonsSample.btnProgressCustom.setOnClickListener(v -> showProgressCustom(binding.layoutButtonsSample.btnProgressCustom));
    }



    private void showProgressLeft(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColor(Color.WHITE);
            progressParams.setGravity(DrawableButton.GRAVITY_TEXT_START);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progress_left_text);
        }, 3000);
    }

    private void showProgressCenter(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setProgressColor(Color.WHITE);
            progressParams.setGravity(DrawableButton.GRAVITY_CENTER);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progress_center_text);
        }, 3000);
    }

    private void showProgressRight(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColor(Color.WHITE);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progress_right_text);
        }, 3000);
    }

    private void showProgressCustom(final Button button) {
        DrawableButtonExtensionsKt.showProgress(button, progressParams -> {
            progressParams.setButtonTextRes(R.string.loading);
            progressParams.setProgressColors(new int[] {Color.WHITE, Color.MAGENTA, Color.GREEN});
            progressParams.setGravity(DrawableButton.GRAVITY_TEXT_END);
            progressParams.setProgressRadiusRes(R.dimen.progressRadius);
            progressParams.setProgressStrokeRes(R.dimen.progressStroke);
            progressParams.setTextMarginRes(R.dimen.textMarginStyled);
            return Unit.INSTANCE;
        });
        button.setEnabled(false);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setEnabled(true);
            DrawableButtonExtensionsKt.hideProgress(button, R.string.progress_custom_text);
        }, 3000);
    }
}
