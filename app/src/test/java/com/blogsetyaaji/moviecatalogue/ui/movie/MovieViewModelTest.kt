package com.blogsetyaaji.moviecatalogue.ui.movie

import androidx.lifecycle.MutableLiveData
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository
import com.blogsetyaaji.moviecatalogue.utils.DataDummy
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`


class MovieViewModelTest : TestCase() {


    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var contentRepository: ContentRepository

    override fun setUp() {
        super.setUp()
        viewModel = MovieViewModel(contentRepository)
    }

    @Test
    fun testGetMovies() {
//        val dummyMovie: MovieResponse = DataDummy.generateDummyMovies()
//        val movies = MutableLiveData<MovieResponse>()
//        movies.setValue(dummyMovie)
//
//        `when`(contentRepository.getRecommendedMovies(API_KEY)).thenReturn(movies)
//        //noinspection unchecked
//        val observer: Observer<MovieResponse> = mock(Observer::class.java)
//        movieViewModel.getRecommendedMovie().observeForever(observer)
//        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun testNull() {

    }
}