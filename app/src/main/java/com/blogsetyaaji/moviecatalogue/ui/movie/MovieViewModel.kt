package com.blogsetyaaji.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.data.Movie
import com.blogsetyaaji.moviecatalogue.utils.MovieDummy

class MovieViewModel: ViewModel() {
    fun getMovies(): List<Movie> = MovieDummy.generateDummyMovies()
}
