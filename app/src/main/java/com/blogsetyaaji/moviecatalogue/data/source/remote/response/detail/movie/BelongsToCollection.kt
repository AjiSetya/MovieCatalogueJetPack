package com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BelongsToCollection(

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
) : Parcelable