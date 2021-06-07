package com.blogsetyaaji.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository

class MovieViewModel(private val contentRepository: ContentRepository): ViewModel() {
    fun getMovie(): LiveData<MovieResponse?>{
        return contentRepository.getAllMovies(BuildConfig.MYAPI_KEY)
    }
}
