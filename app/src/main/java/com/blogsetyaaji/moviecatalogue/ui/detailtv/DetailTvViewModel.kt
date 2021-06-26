package com.blogsetyaaji.moviecatalogue.ui.detailtv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.vo.Resource

class DetailTvViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    lateinit var data: LiveData<Resource<DetailTvResponse>>

    fun getDetailTv(id: Int?): LiveData<Resource<DetailTvResponse>> {
        data = contentRepository.getDetailTv(id, BuildConfig.MYAPI_KEY)
        return data
    }

    fun getFavoriteTv(id: Int?): LiveData<DetailTvResponse> =
        contentRepository.getFavoriteTvById(id)

    fun addTvToFavorite(tv: DetailTvResponse) = contentRepository.setFavoriteTv(tv)

    fun deleteTvFromFavorite(tv: DetailTvResponse) =
        contentRepository.deleteFavoriteTv(tv)
}