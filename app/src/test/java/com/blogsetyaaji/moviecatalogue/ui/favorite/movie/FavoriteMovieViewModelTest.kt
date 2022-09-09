package com.blogsetyaaji.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<DetailMovieResponse>>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(contentRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovie = listOf(DataDummy.generateDummyDetailMovie())
        val movies = MutableLiveData<List<DetailMovieResponse>>()
        movies.value = dummyMovie

        Mockito.`when`(contentRepository.getAllFavoriteMovie()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovie().value
        Mockito.verify(contentRepository).getAllFavoriteMovie()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(1, movieEntities?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }

}