package com.blogsetyaaji.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse

@Dao
interface MovieDao {
    @Query("SELECT * FROM tb_movie")
    fun getMovie(): LiveData<List<MovieEntity?>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity?>?)

    @Query("DELETE FROM tb_movie")
    fun clearMovie()

    @Query("SELECT * FROM tb_fav_movie")
    fun getFavoriteMovie(): LiveData<List<DetailMovieResponse>>

    @Query("SELECT * FROM tb_fav_movie WHERE id = :idMovie")
    fun getFavoriteMovieById(idMovie: Int?): LiveData<DetailMovieResponse?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(movie: DetailMovieResponse?)

    @Delete
    fun deleteFavoriteMovie(movie: DetailMovieResponse?)
}