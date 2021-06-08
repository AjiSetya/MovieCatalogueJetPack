package com.blogsetyaaji.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository
import com.blogsetyaaji.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {


    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<MovieResponse?>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Before
    fun setUp() {
        viewModel = MovieViewModel(contentRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovie: MovieResponse = DataDummy.generateDummyMovies()
        val movies = MutableLiveData<MovieResponse>()
        movies.setValue(dummyMovie)

        `when`(contentRepository.getAllMovies(BuildConfig.MYAPI_KEY)).thenReturn(movies)
        val movieEntities = viewModel.getMovie().value
        verify(contentRepository).getAllMovies(BuildConfig.MYAPI_KEY)
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.results?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

}