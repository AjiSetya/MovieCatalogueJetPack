package com.blogsetyaaji.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
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

    fun getMovies(apiKey: String): LiveData<ApiResponse<List<MovieEntity?>?>> {
        val client = ApiConfig.getApiService().fetchMovie(apiKey)
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieEntity?>?>>()
        client.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    resultMovie.value = ApiResponse.success(response.body()?.results)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                resultMovie.value = ApiResponse.error(t.message.toString(), null)
            }
        })
        return resultMovie
    }

    fun getTv(apiKey: String): LiveData<ApiResponse<List<TvEntity?>?>> {
        val client = ApiConfig.getApiService().fetchTv(apiKey)
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<TvEntity?>?>>()
        client.enqueue(object : Callback<TvResponse> {
            override fun onResponse(
                call: Call<TvResponse>,
                response: Response<TvResponse>
            ) {
                if (response.isSuccessful) {
                    resultTv.value = ApiResponse.success(response.body()?.results)
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                resultTv.value = ApiResponse.error(t.message.toString(), null)
            }
        })

        return resultTv
    }

    fun getDetailMovies(id: Int?, apiKey: String): LiveData<ApiResponse<DetailMovieResponse?>> {
        val client = ApiConfig.getApiService().fetchMovieById(id, apiKey)
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse?>>()
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful) {
                    resultDetailMovie.value = ApiResponse.success(response.body())
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                resultDetailMovie.value = ApiResponse.error(t.message.toString(), null)
            }
        })

        return resultDetailMovie
    }

    fun getDetailTv(id: Int?, apiKey: String): LiveData<ApiResponse<DetailTvResponse?>> {
        val client = ApiConfig.getApiService().fetchTvById(id, apiKey)
        EspressoIdlingResource.increment()
        val resultDetailTv = MutableLiveData<ApiResponse<DetailTvResponse?>>()
        client.enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                if (response.isSuccessful) {
                    resultDetailTv.value = ApiResponse.success(response.body())
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                resultDetailTv.value = ApiResponse.error(t.message.toString(), null)
            }
        })

        return resultDetailTv
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