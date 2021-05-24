package com.blogsetyaaji.moviecatalogue.ui.movie

import com.blogsetyaaji.moviecatalogue.utils.MovieDummy
import junit.framework.TestCase
import org.junit.Test

class MovieViewModelTest : TestCase() {

    private lateinit var viewModel: MovieViewModel

    override fun setUp() {
        super.setUp()
        viewModel = MovieViewModel()
    }

    @Test
    fun testGetMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(MovieDummy.generateDummyMovies().size, movies.size)
    }
}