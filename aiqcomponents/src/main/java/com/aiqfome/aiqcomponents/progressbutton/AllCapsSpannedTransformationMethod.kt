package com.aiqfome.aiqcomponents.progressbutton

import android.graphics.Rect
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.TransformationMethod
import android.view.View

class AllCapsSpannedTransformationMethod : TransformationMethod {

    override fun getTransformation(source: CharSequence?, view: View): CharSequence? {
        if (source == null) {
            return null
        }
        val upperCaseText = source.toString().uppercase()
        if (source is Spanned) {
            val spannable = SpannableString(upperCaseText)
            TextUtils.copySpansFrom(source, 0, source.length, null, spannable, 0)
            return spannable
        } else {
            return upperCaseText
        }
    }

    override fun onFocusChanged(
        view: View, sourceText: CharSequence, focused: Boolean, direction: Int,
        previouslyFocusedRect: Rect?
    ) {
    }

}
