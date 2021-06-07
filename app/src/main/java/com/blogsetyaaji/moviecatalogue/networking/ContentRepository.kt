package com.blogsetyaaji.moviecatalogue.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blogsetyaaji.moviecatalogue.data.source.RemoteDataSource
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse

class ContentRepository private constructor(private val remoteRepository: RemoteDataSource){

    companion object {
        @Volatile
        private var instance: ContentRepository? = null

        fun getInstance(remoteData: RemoteDataSource): ContentRepository =
            instance ?: synchronized(this) {
                ContentRepository(remoteData).apply { instance = this }
            }
    }

    fun getAllMovies(apiKey: String): LiveData<MovieResponse?>{
        val movieResult = MutableLiveData<MovieResponse?>()

        remoteRepository.getMovies(apiKey, object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMoviesReceived(movieResponse: MovieResponse?) {
                if (movieResponse != null) {
                    movieResult.postValue(movieResponse)
                }
            }

            override fun onDataNotAvailabel() {

            }
        })

        return movieResult
    }

    fun getDetailMovie(id: Int?, apiKey: String): LiveData<DetailMovieResponse?>{
        val movieResult = MutableLiveData<DetailMovieResponse?>()

        remoteRepository.getDetailMovies(id, apiKey, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onAllMoviesReceived(movieResponse: DetailMovieResponse?) {
                if (movieResponse != null) {
                    movieResult.postValue(movieResponse)
                }
            }

            override fun onDataNotAvailabel() {

            }
        })

        return movieResult
    }
}