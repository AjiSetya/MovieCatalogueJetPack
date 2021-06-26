package com.blogsetyaaji.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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

    fun getAllMovies(apiKey: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieEntity?>?>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MovieEntity?>?>> =
                remoteRepository.getMovies(apiKey)


            override fun saveCallResult(data: List<MovieEntity?>?) =
                localDataSource.addMovie(data)

        }.asLiveData()
    }

    fun getAllTv(apiKey: String): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvEntity?>?>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<TvEntity?>?>> =
                remoteRepository.getTv(apiKey)


            override fun saveCallResult(data: List<TvEntity?>?) =
                localDataSource.addTv(data)


        }.asLiveData()
    }

    fun getDetailMovie(id: Int?, apiKey: String): LiveData<Resource<DetailMovieResponse>> =
        remoteRepository.getDetailMovies(id, apiKey)

    fun getDetailTv(id: Int?, apiKey: String): LiveData<Resource<DetailTvResponse>> =
        remoteRepository.getDetailTv(id, apiKey)


    fun setFavoriteMovie(movie: DetailMovieResponse) =
        appExecutors.diskIO().execute { localDataSource.addFavoriteMovie(movie) }

    fun getFavoriteMovieById(id: Int?) = localDataSource.getFavoriteMovieById(id)

    fun getAllFavoriteMovie() = localDataSource.getFavoriteMovies()

    fun deleteFavoriteMovie(movie: DetailMovieResponse) =
        appExecutors.diskIO().execute { localDataSource.deleteFavoriteMovie(movie) }


    fun setFavoriteTv(tv: DetailTvResponse) =
        appExecutors.diskIO().execute { localDataSource.addFavoriteTv(tv) }

    fun getFavoriteTvById(id: Int?) = localDataSource.getFavoriteTvById(id)

    fun getAllFavoriteTv() = localDataSource.getFavoriteTv()

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