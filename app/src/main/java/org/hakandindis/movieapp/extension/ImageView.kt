package org.hakandindis.movieapp.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.centerCropTransform
import org.hakandindis.movieapp.R
import org.hakandindis.movieapp.util.ApiConstants


fun ImageView.loadCircleImage(path: String?) {
    Glide.with(this.context)
        .load("${ApiConstants.BASE_IMAGE_URL}$path")
        .apply(centerCropTransform().error(R.drawable.ic_error).circleCrop())
        .into(this)
}


fun ImageView.loadImage(path: String?) {
    Glide.with(this.context)
        .load("${ApiConstants.BASE_IMAGE_URL}$path")
        .apply(centerCropTransform().error(R.drawable.ic_error))
        .into(this)
}