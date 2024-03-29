package com.blogsetyaaji.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_tv")
@Parcelize
data class TvEntity(

	@field:SerializedName("first_air_date")
	val firstAirDate: String?,

	@field:SerializedName("overview")
	val overview: String?,

	@field:SerializedName("original_language")
	val originalLanguage: String?,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>?,

	@field:SerializedName("poster_path")
	val posterPath: String?,

	@field:SerializedName("origin_country")
	val originCountry: List<String>?,

	@field:SerializedName("backdrop_path")
	val backdropPath: String?,

	@field:SerializedName("original_name")
	val originalName: String?,

	@field:SerializedName("popularity")
	val popularity: Double?,

	@field:SerializedName("vote_average")
	val voteAverage: Double?,

	@field:SerializedName("name")
	val name: String?,

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_count")
	val voteCount: Int?
) : Parcelable
