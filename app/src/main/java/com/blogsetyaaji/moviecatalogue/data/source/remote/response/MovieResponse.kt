package com.blogsetyaaji.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<MovieEntity?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
) : Parcelable