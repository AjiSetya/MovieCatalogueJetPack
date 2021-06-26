package com.blogsetyaaji.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse

@Dao
interface TvDao {
    @Query("SELECT * FROM tb_tv")
    fun getTv(): DataSource.Factory<Int, TvEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvEntity?>?)

    @Query("DELETE FROM tb_tv")
    fun clearTv()

    @Query("SELECT * FROM tb_fav_tv")
    fun getFavoriteTv(): LiveData<List<DetailTvResponse>>

    @Query("SELECT * FROM tb_fav_tv WHERE id = :idTv")
    fun getFavoriteTvById(idTv: Int?): LiveData<DetailTvResponse?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTv(tv: DetailTvResponse?)

    @Delete
    fun deleteFavoriteTv(tv: DetailTvResponse?)
}