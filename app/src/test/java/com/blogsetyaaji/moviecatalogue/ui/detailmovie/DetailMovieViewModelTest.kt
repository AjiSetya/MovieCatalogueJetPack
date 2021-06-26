package com.blogsetyaaji.moviecatalogue.ui.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.utils.DataDummy
import com.blogsetyaaji.moviecatalogue.vo.Resource
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
class DetailMovieViewModelTest {


    private lateinit var viewModel: DetailMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<DetailMovieResponse>>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(contentRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovie= Resource.success(DataDummy.generateDummyDetailMovie())
        val detailMovies = MutableLiveData<Resource<DetailMovieResponse>>()
        detailMovies.value = dummyMovie

        `when`(contentRepository.getDetailMovie(399566, BuildConfig.MYAPI_KEY)).thenReturn(
            detailMovies
        )
        val detail = viewModel.getDetailMovie(399566).value
        verify(contentRepository).getDetailMovie(399566, BuildConfig.MYAPI_KEY)
        assertNotNull(detail)
        assertEquals(dummyMovie.data?.title, detail?.data?.title)

        viewModel.getDetailMovie(399566).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}