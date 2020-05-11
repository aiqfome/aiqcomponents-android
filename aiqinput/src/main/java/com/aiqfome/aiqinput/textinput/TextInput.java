package com.aiqfome.aiqinput.textinput;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.aiqfome.aiqinput.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TextInput extends ConstraintLayout {

    private static final String TAG = TextInput.class.getSimpleName();

    private TextInputController controller;
    
    private MaterialCardView selector;
    private TextInputEditText input;
    private TextInputLayout inputLayout;
    private ImageView icon;

    public TextInput(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.text_input, this, true);

        selector = findViewById(R.id.selector);
        input = findViewById(R.id.et_input);
        inputLayout = findViewById(R.id.input);
        icon = findViewById(R.id.iv_selected_icon);

        setupAttrs(context, attrs);
        setOnClickListener(onClickListener());

        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute
                (android.R.attr.selectableItemBackground, outValue, true);

        getRootView().setBackgroundResource(outValue.resourceId);
    }

    private void setupAttrs(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) return;

        int defaultBackgroundColor = getResources().getColor(R.color.colorBackground);
        selector.setCardBackgroundColor(defaultBackgroundColor);

        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.TextInput);

        int attrsSize = styledAttributes.getIndexCount();

        for (int i = 0; i < attrsSize; i++) {
            int attr = styledAttributes.getIndex(i);

            if (attr == R.styleable.TextInput_backgroundColor) {
                if (styledAttributes.hasValue(attr)) {
                    int backgroundColor = styledAttributes
                            .getColor(attr, getResources().getColor(R.color.colorBackground));

                    selector.setCardBackgroundColor(backgroundColor);
                    input.setBackgroundColor(backgroundColor);
                }

            } else if (attr == R.styleable.TextInput_textAppearance) {
                int textAppearance = styledAttributes.getResourceId(attr, -1);
                input.setTextAppearance(context, textAppearance);

            } else if (attr == R.styleable.TextInput_selectorDefaultIcon) {
                if (styledAttributes.hasValue(attr)) {
                    Drawable defaultIcon = styledAttributes.getDrawable(attr);
                    icon.setImageDrawable(defaultIcon);
                }

            } else if (attr == R.styleable.TextInput_android_digits) {
                if (styledAttributes.hasValue(attr)) {
                    String digits = styledAttributes.getString(attr);

                    if (digits != null)
                        input.setKeyListener(DigitsKeyListener.getInstance(digits));
                }

            } else if (attr == R.styleable.TextInput_android_inputType) {
                int inputType = styledAttributes
                        .getInt(attr, EditorInfo.TYPE_TEXT_VARIATION_NORMAL);

                input.setInputType(inputType);

            } else if (attr == R.styleable.TextInput_android_selectAllOnFocus) {
                boolean selectAllOnFocus = styledAttributes.getBoolean(attr, false);
                input.setSelectAllOnFocus(selectAllOnFocus);

            } else if (attr == R.styleable.TextInput_android_hint) {
                if (styledAttributes.hasValue(attr)) {
                    String hint = styledAttributes.getString(attr);
                    if (hint != null) inputLayout.setHint(hint);
                }

            } else if (attr == R.styleable.TextInput_android_imeOptions) {
                int imeOptions = styledAttributes.getInt(attr, 0);
                input.setImeOptions(imeOptions);

            } else if (attr == R.styleable.TextInput_android_text) {
                if (styledAttributes.hasValue(attr)) {
                    String text = styledAttributes.getString(attr);

                    if (text != null) input.setText(text);
                }

            } else {
                Log.d("aiqInput", "Unknown attribute for " + getClass().toString() + ": " + attr);
            }
        }

        styledAttributes.recycle();
    }

    private OnClickListener onClickListener() {
        return v -> {
            if (controller != null) controller.show();
            else Log.e(TAG, "no controller found, please setup TextInput Component!");
        };
    }

    public void setup(TextInputController controller) {
        controller.setTextInput(this);
        this.controller = controller;
    }

    public void setSelectedItem(Drawable icon) {
        this.icon.setImageDrawable(icon);
    }

    public TextInputController getController() {
        return controller;
    }

    public MaterialCardView getSelector() {
        return selector;
    }

    public TextInputEditText getInput() {
        return input;
    }

    public TextInputLayout getInputLayout() {
        return inputLayout;
    }

    public ImageView getIcon() {
        return icon;
    }
}
