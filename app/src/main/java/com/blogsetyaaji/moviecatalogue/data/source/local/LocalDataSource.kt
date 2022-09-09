package com.blogsetyaaji.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.data.source.local.room.MovieDao
import com.blogsetyaaji.moviecatalogue.data.source.local.room.TvDao
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse

class LocalDataSource private constructor(
    private val movieDao: MovieDao,
    private val tvDao: TvDao
) {

    fun getAllMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getMovie()

    fun addMovie(movie: List<MovieEntity?>?) = movieDao.insertMovie(movie)

    fun clearAllMovie() = movieDao.clearMovie()

    fun getFavoriteMovies(): LiveData<List<DetailMovieResponse>> = movieDao.getFavoriteMovie()

    fun getFavoriteMovieById(idMovie: Int?): LiveData<DetailMovieResponse> =
        movieDao.getFavoriteMovieById(idMovie)

    fun addFavoriteMovie(movie: DetailMovieResponse?) = movieDao.insertFavoriteMovie(movie)

    fun deleteFavoriteMovie(movie: DetailMovieResponse?) = movieDao.deleteFavoriteMovie(movie)


    fun getAllTv(): DataSource.Factory<Int, TvEntity> = tvDao.getTv()

    fun addTv(tv: List<TvEntity?>?) = tvDao.insertTv(tv)

    fun clearAllTv() = tvDao.clearTv()

    fun getFavoriteTv(): LiveData<List<DetailTvResponse>> = tvDao.getFavoriteTv()

    fun getFavoriteTvById(idMovie: Int?): LiveData<DetailTvResponse> =
        tvDao.getFavoriteTvById(idMovie)

    fun addFavoriteTv(movie: DetailTvResponse?) = tvDao.insertFavoriteTv(movie)

    fun deleteFavoriteTv(tv: DetailTvResponse?) = tvDao.deleteFavoriteTv(tv)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao, tvDao: TvDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao, tvDao).apply { INSTANCE = this }
    }
}