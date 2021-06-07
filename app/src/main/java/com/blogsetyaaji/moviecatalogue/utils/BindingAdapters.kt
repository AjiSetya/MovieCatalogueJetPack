package com.blogsetyaaji.moviecatalogue.utils

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.blogsetyaaji.moviecatalogue.networking.ImageNetwork.getBackdropPath
import com.blogsetyaaji.moviecatalogue.networking.ImageNetwork.getPosterPath

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

@BindingAdapter("imageNetwork")
fun imageNetwork(view: ImageView, imageUrl: String?) {
    imageUrl?.let { view.load(getPosterPath(it)) }
}

@BindingAdapter("imageBgNetwork")
fun imageBgNetwork(view: ImageView, imageUrl: String?) {
    imageUrl?.let { view.load(getBackdropPath(it)) }
}