package com.blogsetyaaji.moviecatalogue.data.source

import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.TvResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.networking.ApiConfig
import com.blogsetyaaji.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovies(apiKey: String, callback: LoadMovieCallback) {
        val client = ApiConfig.getApiService().fetchMovie(apiKey)
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    callback.onAllMoviesReceived(response.body())
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback.onDataNotAvailabel(t.message)
            }
        })
    }

    fun getTv(apiKey: String, callback: LoadTvCallback) {
        val client = ApiConfig.getApiService().fetchTv(apiKey)
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                if (response.isSuccessful) {
                    callback.onAllTvReceived(response.body())
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                callback.onDataNotAvailabel(t.message)
            }
        })
    }

    fun getDetailMovies(id: Int?, apiKey: String, callback: LoadDetailMovieCallback) {
        val client = ApiConfig.getApiService().fetchMovieById(id, apiKey)
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onAllMoviesReceived(response.body())
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                callback.onDataNotAvailabel(t.message)
            }
        })
    }

    fun getDetailTv(id: Int?, apiKey: String, callback: LoadDetailTvCallback) {
        val client = ApiConfig.getApiService().fetchTvById(id, apiKey)
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                if (response.isSuccessful) {
                    callback.onAllTvReceived(response.body())
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                callback.onDataNotAvailabel(t.message)
            }
        })
    }

    interface LoadMovieCallback {
        fun onAllMoviesReceived(movieResponse: MovieResponse?)
        fun onDataNotAvailabel(message: String?)
    }

    interface LoadDetailMovieCallback {
        fun onAllMoviesReceived(movieResponse: DetailMovieResponse?)
        fun onDataNotAvailabel(message: String?)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: TvResponse?)
        fun onDataNotAvailabel(message: String?)
    }

    interface LoadDetailTvCallback {
        fun onAllTvReceived(detailTvResponse: DetailTvResponse?)
        fun onDataNotAvailabel(message: String?)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource().apply { instance = this }
            }
    }

}