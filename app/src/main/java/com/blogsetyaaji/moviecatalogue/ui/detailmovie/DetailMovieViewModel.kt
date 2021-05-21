package com.blogsetyaaji.moviecatalogue.ui.detailmovie

import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.data.Movie
import com.blogsetyaaji.moviecatalogue.utils.MovieDummy

class DetailMovieViewModel : ViewModel() {
    private val listDataMovies: List<Movie> = MovieDummy.generateDummyMovies()
    var data: Movie? = null

    fun getMovieByPosition(position: Int) {
        data = listDataMovies[position]
    }
}