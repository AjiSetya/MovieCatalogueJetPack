package com.blogsetyaaji.moviecatalogue.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.vo.Resource

class DetailMovieViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    var data: LiveData<Resource<DetailMovieResponse?>>? = null

    fun getDetailMovie(id: Int?): LiveData<Resource<DetailMovieResponse?>>? {
        data = contentRepository.getDetailMovie(id, BuildConfig.MYAPI_KEY)
        return data
    }
}