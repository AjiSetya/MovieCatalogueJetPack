package com.blogsetyaaji.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.data.ContentRepository

class FavoriteMovieViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getFavoriteMovie() = contentRepository.getAllFavoriteMovie()

}