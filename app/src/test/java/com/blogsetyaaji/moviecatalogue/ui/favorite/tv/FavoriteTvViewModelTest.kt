package com.blogsetyaaji.moviecatalogue.ui.favorite.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
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
class FavoriteTvViewModelTest {

    private lateinit var viewModel: FavoriteTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<DetailTvResponse>>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Before
    fun setUp() {
        viewModel = FavoriteTvViewModel(contentRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyTv = listOf(DataDummy.generateDummyDetailTv())
        val tv = MutableLiveData<List<DetailTvResponse>>()
        tv.value = dummyTv

        Mockito.`when`(contentRepository.getAllFavoriteTv()).thenReturn(tv)
        val movieEntities = viewModel.getFavoriteTv().value
        Mockito.verify(contentRepository).getAllFavoriteTv()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(1, movieEntities?.size)

        viewModel.getFavoriteTv().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }

}