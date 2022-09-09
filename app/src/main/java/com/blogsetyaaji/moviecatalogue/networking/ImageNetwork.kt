package com.blogsetyaaji.moviecatalogue.networking

object ImageNetwork {

    private const val BASE_POSTER = "https://image.tmdb.org/t/p/w342"
    private const val BASE_BACKDROP = "https://image.tmdb.org/t/p/w780"

    fun getPosterPath(posterPath: String): String {
        return BASE_POSTER + posterPath
    }

    fun getBackdropPath(backdropPath: String): String {
        return BASE_BACKDROP + backdropPath
    }
}