package com.aiqfome.aiqcomponents.selector;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.aiqfome.aiqcomponents.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Selector extends LinearLayout {

    private static final String TAG = Selector.class.getSimpleName();

    private SelectorController controller;

    private LinearLayout root;
    private TextInputLayout inputLayout;
    private TextInputEditText editText;

    public Selector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.selector, this, true);

        root = findViewById(R.id.root);
        inputLayout = findViewById(R.id.input_layout);
        editText = findViewById(R.id.et_input);
        editText.setKeyListener(null);
        Drawable icChevronDown = VectorDrawableCompat.create(context.getResources(),
                R.drawable.ic_chevron_down, context.getTheme());
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, icChevronDown, null);

        setupAttrs(context, attrs);

        setOnClickListener(onClickListener());
        root.setOnClickListener(onClickListener());
        inputLayout.setOnClickListener(onClickListener());
        editText.setOnClickListener(onClickListener());

        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute
                (android.R.attr.selectableItemBackground, outValue, true);

        getRootView().setBackgroundResource(outValue.resourceId);
    }

    private void setupAttrs(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) return;

        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.Selector);

        int attrsSize = styledAttributes.getIndexCount();

        for (int i = 0; i < attrsSize; i++) {
            int attr = styledAttributes.getIndex(i);

            if (attr == R.styleable.Selector_selectorBackgroundColor) {
                if (styledAttributes.hasValue(attr)) {
                    int backgroundColor = styledAttributes
                            .getColor(attr, getResources().getColor(R.color.colorBackground));

                    editText.setBackgroundColor(backgroundColor);
                }

            } else if (attr == R.styleable.Selector_selectorTextAppearance) {
                int textAppearance = styledAttributes.getResourceId(attr, -1);
                editText.setTextAppearance(context, textAppearance);

            } else if (attr == R.styleable.Selector_selectorTitle) {
                if (styledAttributes.hasValue(attr)) {
                    String hint = styledAttributes.getString(attr);
                    if (hint != null) inputLayout.setHint(hint);
                }

            } else {
                Log.d(TAG, "Unknown attribute for " + getClass().toString() + ": " + attr);
            }
        }

        styledAttributes.recycle();
    }

    private OnClickListener onClickListener() {
        return v -> {
            if (controller != null) controller.show();
            else Log.e(TAG, "no controller found, please setup Selector Component!");
        };
    }

    public void setup(SelectorController controller) {
        controller.setSelector(this);
        this.controller = controller;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        super.setClickable(enabled);

        inputLayout.setEnabled(enabled);
        inputLayout.setClickable(enabled);

        editText.setEnabled(enabled);
        editText.setClickable(enabled);

        if (!enabled) {
            int color = ContextCompat.getColor(getContext(), R.color.colorDivider);
            editText.setBackgroundColor(color);
        }
    }

    public void setSelectedItem(String itemText) {
        this.editText.setText(itemText);
    }


    public SelectorController getController() {
        return controller;
    }

    public TextInputLayout getInputLayout() {
        return inputLayout;
    }

    public TextInputEditText getEditText() {
        return editText;
    }
}
