package com.blogsetyaaji.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
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
class TvViewModelTest {


    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var contentRepository: ContentRepository

    @Mock
    private lateinit var pagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = TvViewModel(contentRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tv.value = dummyTv

        `when`(contentRepository.getAllTv(BuildConfig.MYAPI_KEY)).thenReturn(tv)
        val tvEntities = viewModel.getTv().value?.data
        verify(contentRepository).getAllTv(BuildConfig.MYAPI_KEY)
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

}