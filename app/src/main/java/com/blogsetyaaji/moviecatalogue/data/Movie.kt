package com.blogsetyaaji.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var name : String,
    var poster : Int,
    var rating : Double,
    var overview : String,
    var relaseDate: String
) : Parcelable
