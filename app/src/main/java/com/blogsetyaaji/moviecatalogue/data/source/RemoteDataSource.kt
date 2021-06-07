package com.blogsetyaaji.moviecatalogue.data.source

import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.networking.ApiConfig
import com.blogsetyaaji.moviecatalogue.networking.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }
    }

    fun getMovies(apiKey: String, callback: LoadMovieCallback) {
        val client = ApiConfig.getApiService().fetchMovie(apiKey)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    callback.onAllMoviesReceived(response.body())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback.onDataNotAvailabel()
            }
        })
    }

    fun getDetailMovies(id: Int?, apiKey: String, callback: LoadDetailMovieCallback) {
        val client = ApiConfig.getApiService().fetchMovieById(id, apiKey)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onAllMoviesReceived(response.body())
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                callback.onDataNotAvailabel()
            }
        })
    }

    interface LoadMovieCallback {
        fun onAllMoviesReceived(movieResponse: MovieResponse?)
        fun onDataNotAvailabel()
    }

    interface LoadDetailMovieCallback {
        fun onAllMoviesReceived(movieResponse: DetailMovieResponse?)
        fun onDataNotAvailabel()
    }
}