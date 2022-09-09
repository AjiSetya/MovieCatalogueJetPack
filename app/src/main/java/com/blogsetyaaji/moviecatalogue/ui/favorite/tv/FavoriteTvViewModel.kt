package com.blogsetyaaji.moviecatalogue.ui.favorite.tv

import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.data.ContentRepository

class FavoriteTvViewModel(private val contentRepository: ContentRepository) : ViewModel() {

    fun getFavoriteTv() = contentRepository.getAllFavoriteTv()

}