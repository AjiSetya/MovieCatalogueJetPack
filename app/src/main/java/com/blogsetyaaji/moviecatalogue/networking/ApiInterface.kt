package com.blogsetyaaji.moviecatalogue.networking

import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    // discover movie
    @GET("/3/discover/movie?language=en-US")
    fun fetchMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    // discover tv
    /*@GET("/3/discover/tv?language=en-US")
    fun fetchTv(): Call<TvResponse>*/

    // get detail movie
    @GET("/3/movie/{movie_id}")
    fun fetchMovieById(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    // get detail tv
    /*@GET("/3/tv/{tv_id}")
    fun fetchTvById(@Path("tv_id") id: Int): Call<DetailTvResponse>*/
}