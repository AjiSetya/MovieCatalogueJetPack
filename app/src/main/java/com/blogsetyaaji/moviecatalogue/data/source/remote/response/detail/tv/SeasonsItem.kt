package com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeasonsItem(

	@field:SerializedName("air_date")
	val airDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("episode_count")
	val episodeCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("season_number")
	val seasonNumber: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
) : Parcelable