package com.blogsetyaaji.moviecatalogue.utils

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.RoundedCornersTransformation

@BindingAdapter("image")
fun image(view: ImageView, imageUrl: Int?) {
    imageUrl?.let {
        view.load(it) {
            transformations(RoundedCornersTransformation(5f))
        }
    }
}

@BindingAdapter("rating")
fun rating(view: RatingBar, rating: Double?) {
    view.rating = (rating ?: 0.0).div(2).toFloat()
}

@BindingAdapter("intToString")
fun intToString(view: TextView, int: Int) {
    view.text = int.toString()
}

@BindingAdapter("doubleToString")
fun dobleToString(view: TextView, double: Double) {
    view.text = double.toString()
}