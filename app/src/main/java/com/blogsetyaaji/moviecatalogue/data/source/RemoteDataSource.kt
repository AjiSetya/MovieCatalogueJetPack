package com.blogsetyaaji.moviecatalogue.data.source

import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.TvResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.networking.ApiConfig
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

    fun getTv(apiKey: String, callback: LoadTvCallback) {
        val client = ApiConfig.getApiService().fetchTv(apiKey)
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                if (response.isSuccessful) {
                    callback.onAllTvReceived(response.body())
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
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

    fun getDetailTv(id: Int?, apiKey: String, callback: LoadDetailTvCallback) {
        val client = ApiConfig.getApiService().fetchTvById(id, apiKey)
        client.enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onAllTvReceived(response.body())
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
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

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: TvResponse?)
        fun onDataNotAvailabel()
    }

    interface LoadDetailTvCallback {
        fun onAllTvReceived(detailTvResponse: DetailTvResponse?)
        fun onDataNotAvailabel()
    }

}