package fsa.android.nasa.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

fun coloredText(spannableString : SpannableString,color:Int): SpannableString {
    spannableString.setSpan(
        ForegroundColorSpan(color),
        0, spannableString.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    spannableString.setSpan(
        StyleSpan(Typeface.BOLD),
        0, spannableString.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    return spannableString
}