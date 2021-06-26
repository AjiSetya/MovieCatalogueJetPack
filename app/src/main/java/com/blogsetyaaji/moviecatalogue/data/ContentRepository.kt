package com.blogsetyaaji.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.blogsetyaaji.moviecatalogue.data.source.local.LocalDataSource
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.data.source.remote.ApiResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.RemoteDataSource
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.utils.AppExecutors
import com.blogsetyaaji.moviecatalogue.vo.Resource

class ContentRepository private constructor(
    private val remoteRepository: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    fun getAllMovies(apiKey: String): LiveData<Resource<List<MovieEntity?>?>> {
        return object : NetworkBoundResource<List<MovieEntity?>?, List<MovieEntity?>?>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity?>?> =
                localDataSource.getAllMovie()


            override fun shouldFetch(data: List<MovieEntity?>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MovieEntity?>?>> =
                remoteRepository.getMovies(apiKey)


            override fun saveCallResult(data: List<MovieEntity?>?) =
                localDataSource.addMovie(data)

        }.asLiveData()
    }

    fun getAllTv(apiKey: String): LiveData<Resource<List<TvEntity?>?>> {
        return object : NetworkBoundResource<List<TvEntity?>?, List<TvEntity?>?>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvEntity?>?> =
                localDataSource.getAllTv()


            override fun shouldFetch(data: List<TvEntity?>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<TvEntity?>?>> =
                remoteRepository.getTv(apiKey)


            override fun saveCallResult(data: List<TvEntity?>?) =
                localDataSource.addTv(data)


        }.asLiveData()
    }

    fun getDetailMovie(id: Int?, apiKey: String): LiveData<Resource<DetailMovieResponse?>> {
        return object :
            NetworkBoundResource<DetailMovieResponse?, DetailMovieResponse?>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailMovieResponse?> =
                localDataSource.getFavoriteMovieById(id)


            override fun shouldFetch(data: DetailMovieResponse?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse?>> =
                remoteRepository.getDetailMovies(id, apiKey)

            override fun saveCallResult(data: DetailMovieResponse?) =
                localDataSource.addFavoriteMovie(data)

        }.asLiveData()
    }

    fun getDetailTv(id: Int?, apiKey: String): LiveData<Resource<DetailTvResponse?>> {
        return object : NetworkBoundResource<DetailTvResponse?, DetailTvResponse?>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailTvResponse?> =
                localDataSource.getFavoriteTvById(id)

            override fun shouldFetch(data: DetailTvResponse?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse?>> =
                remoteRepository.getDetailTv(id, apiKey)

            override fun saveCallResult(data: DetailTvResponse?) =
                localDataSource.addFavoriteTv(data)

        }.asLiveData()
    }

    fun getAllFavoriteMovie() =
        appExecutors.diskIO().execute { localDataSource.getFavoriteMovies() }

    fun getAllFavoriteTv() = appExecutors.diskIO().execute { localDataSource.getFavoriteTv() }

    fun deleteFavoriteMovie(movie: DetailMovieResponse) =
        appExecutors.diskIO().execute { localDataSource.deleteFavoriteMovie(movie) }

    fun deleteFavoriteTv(tv: DetailTvResponse) =
        appExecutors.diskIO().execute { localDataSource.deleteFavoriteTv(tv) }

    companion object {
        @Volatile
        private var instance: ContentRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): ContentRepository =
            instance ?: synchronized(this) {
                ContentRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }
}