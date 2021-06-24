package com.blogsetyaaji.moviecatalogue.ui.detailtv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
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
class DetailTvViewModelTest {


    private lateinit var viewModel: DetailTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<DetailTvResponse?>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(contentRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyTv: DetailTvResponse = DataDummy.generateDummyDetailTv()
        val detailTv = MutableLiveData<DetailTvResponse>()
        detailTv.setValue(dummyTv)

        `when`(contentRepository.getDetailTv(399566, BuildConfig.MYAPI_KEY)).thenReturn(
            detailTv
        )
        val detail = viewModel.getDetailTv(399566)?.value
        verify(contentRepository).getDetailTv(399566, BuildConfig.MYAPI_KEY)
        assertNotNull(detail)
        assertEquals(dummyTv.name, detail?.name)

        viewModel.getDetailTv(399566)?.observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}