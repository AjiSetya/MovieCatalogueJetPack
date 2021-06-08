package com.blogsetyaaji.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.MovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.TvResponse
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository
import com.blogsetyaaji.moviecatalogue.ui.movie.MovieViewModel
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
class TvViewModelTest {


    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<TvResponse?>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Before
    fun setUp() {
        viewModel = TvViewModel(contentRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyTv: TvResponse = DataDummy.generateDummyTv()
        val tv = MutableLiveData<TvResponse>()
        tv.setValue(dummyTv)

        `when`(contentRepository.getAllTv(BuildConfig.MYAPI_KEY)).thenReturn(tv)
        val tvEntities = viewModel.getTv().value
        verify(contentRepository).getAllTv(BuildConfig.MYAPI_KEY)
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.results?.size)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

}