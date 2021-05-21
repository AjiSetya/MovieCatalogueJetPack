package com.blogsetyaaji.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tv(
    var name: String,
    var poster: Int,
    var rating: Double,
    var overview: String,
    var relaseDate: String,
    var episode: Int
) : Parcelable
