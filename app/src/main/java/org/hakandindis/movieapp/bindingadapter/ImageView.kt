package org.hakandindis.movieapp.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.hakandindis.movieapp.extension.loadCircleImage
import org.hakandindis.movieapp.extension.loadImage


@BindingAdapter("set_circular_thumbnail")
fun setCircularThumbnail(imageView: ImageView, path: String?) {
    imageView.loadCircleImage(path)
}

@BindingAdapter("set_rectangle_thumbnail")
fun setRectangleThumbnail(imageView: ImageView, path: String?) {
    imageView.loadImage(path)
}

