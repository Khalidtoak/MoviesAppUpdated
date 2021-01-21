package com.example.technicaltest.util

import android.widget.ImageView
import coil.transform.RoundedCornersTransformation
import coil.load
import com.example.technicaltest.R

/**
 * Created by khalidtoak on 1/20/21.
 */

fun ImageView.load(imagePath: String?) {
    if (!imagePath.isNullOrEmpty()) {
        load("https://image.tmdb.org/t/p/w500$imagePath") {
            crossfade(true)
            error(R.drawable.ic_image)
            transformations(RoundedCornersTransformation(10F))
        }
    }
}