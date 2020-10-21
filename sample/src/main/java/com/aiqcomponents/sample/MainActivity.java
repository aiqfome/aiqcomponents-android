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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }
}
