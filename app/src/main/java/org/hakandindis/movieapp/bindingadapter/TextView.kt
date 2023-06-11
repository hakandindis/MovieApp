package org.hakandindis.movieapp.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("set_gender")
fun setGender(textView: TextView, gender: Int?) {
    val result = if (gender == 1) "Female" else "Male"
    textView.text = result
}