package com.blogsetyaaji.moviecatalogue.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository

class DetailMovieViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    var data: LiveData<DetailMovieResponse?>? = null

    fun getDetailMovie(id: Int?): LiveData<DetailMovieResponse?>? {
        data = contentRepository.getDetailMovie(id, BuildConfig.MYAPI_KEY)
        return data
    }
}