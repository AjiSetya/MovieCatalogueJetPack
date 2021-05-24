package com.blogsetyaaji.moviecatalogue.ui.detailmovie

import com.blogsetyaaji.moviecatalogue.utils.MovieDummy
import junit.framework.TestCase
import kotlin.random.Random

class DetailMovieViewModelTest : TestCase() {
    private val randomNumber = Random.nextInt(0, MovieDummy.generateDummyMovies().size - 1)
    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val movieDummy = MovieDummy.generateDummyMovies()[randomNumber]

    override fun setUp() {
        super.setUp()
        detailMovieViewModel = DetailMovieViewModel()
    }

    fun testGetMovieByPosition() {
        detailMovieViewModel.getMovieByPosition(randomNumber)
        val result = detailMovieViewModel.data

        assertNotNull(result)
        assertEquals(movieDummy.name, result?.name)
        assertEquals(movieDummy.overview, result?.overview)
        assertEquals(movieDummy.rating, result?.rating)
        assertEquals(movieDummy.poster, result?.poster)
        assertEquals(movieDummy.relaseDate, result?.relaseDate)
    }
}