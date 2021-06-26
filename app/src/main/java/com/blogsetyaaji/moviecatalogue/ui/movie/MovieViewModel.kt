package com.blogsetyaaji.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.vo.Resource

class MovieViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    fun getMovie(): LiveData<Resource<List<MovieEntity?>?>> =
        contentRepository.getAllMovies(BuildConfig.MYAPI_KEY)
}
