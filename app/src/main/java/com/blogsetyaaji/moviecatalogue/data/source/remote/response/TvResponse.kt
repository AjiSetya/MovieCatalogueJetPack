package com.blogsetyaaji.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvResponse(

    @field:SerializedName("page")
	val page: Int,

    @field:SerializedName("total_pages")
	val totalPages: Int,

    @field:SerializedName("results")
	val results: List<TvEntity>,

    @field:SerializedName("total_results")
	val totalResults: Int
) : Parcelable