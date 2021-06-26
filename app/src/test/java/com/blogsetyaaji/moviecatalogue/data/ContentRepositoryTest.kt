package com.blogsetyaaji.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.local.LocalDataSource
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.data.source.remote.RemoteDataSource
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.utils.AppExecutors
import com.blogsetyaaji.moviecatalogue.utils.DataDummy
import com.blogsetyaaji.moviecatalogue.utils.PagedListUtil
import com.blogsetyaaji.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ContentRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val executors = mock(AppExecutors::class.java)

    private val contentRepository = FakeContentRepository(remote, local, executors)

    private val movieResponse = DataDummy.generateDummyMovieResponse()
    private val tvResponse = DataDummy.generateDummyTvResponse()
    private val detailMovieResponse = DataDummy.generateDummyDetailMovie()
    private val detailTvResponse = DataDummy.generateDummyDetailTv()

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovie()).thenReturn(dataSourceFactory)
        contentRepository.getAllMovies(BuildConfig.MYAPI_KEY)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovieEntities()))
        verify(local).getAllMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.results?.size?.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTv(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        `when`(local.getAllTv()).thenReturn(dataSourceFactory)
        contentRepository.getAllTv(BuildConfig.MYAPI_KEY)

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvEntities()))
        verify(local).getAllTv()
        assertNotNull(tvEntities.data)
        assertEquals(tvResponse.results.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie(){
        contentRepository.getDetailMovie(399566 ,BuildConfig.MYAPI_KEY)
        verify(remote).getDetailMovies(399566 ,BuildConfig.MYAPI_KEY)
        val movieEntities = Resource.success(DataDummy.generateDummyDetailMovie())
        assertNotNull(movieEntities.data)
        assertEquals(detailMovieResponse.title, movieEntities.data?.title)
    }

    @Test
    fun getDetailTv(){
        contentRepository.getDetailTv(60735 ,BuildConfig.MYAPI_KEY)
        verify(remote).getDetailTv(60735 ,BuildConfig.MYAPI_KEY)
        val movieEntities = Resource.success(DataDummy.generateDummyDetailTv())
        assertNotNull(movieEntities.data)
        assertEquals(detailTvResponse.name, movieEntities.data?.name)
    }

    @Test
    fun getAllFavoriteMovie() {
        val dummyDetail = listOf(detailMovieResponse)
        val movies = MutableLiveData<List<DetailMovieResponse>>()
        movies.value = dummyDetail

        `when`(local.getFavoriteMovies()).thenReturn(movies)
        contentRepository.getAllFavoriteMovie()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(dummyDetail))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities.data)
        assertEquals(dummyDetail.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllFavoriteTv() {
        val dummyDetail = listOf(detailTvResponse)
        val tv = MutableLiveData<List<DetailTvResponse>>()
        tv.value = dummyDetail

        `when`(local.getFavoriteTv()).thenReturn(tv)
        contentRepository.getAllFavoriteTv()

        val tvEntities =
            Resource.success(PagedListUtil.mockPagedList(dummyDetail))
        verify(local).getFavoriteTv()
        assertNotNull(tvEntities.data)
        assertEquals(dummyDetail.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovieById(){
        contentRepository.getFavoriteMovieById(399566)
        verify(local).getFavoriteMovieById(399566)
        val movieEntities = Resource.success(DataDummy.generateDummyDetailMovie())
        assertNotNull(movieEntities.data)
        assertEquals(detailMovieResponse.title, movieEntities.data?.title)
    }

    @Test
    fun getFavoriteTvById(){
        contentRepository.getFavoriteTvById(60735)
        verify(local).getFavoriteTvById(60735)
        val tvEntities = Resource.success(DataDummy.generateDummyDetailTv())
        assertNotNull(tvEntities.data)
        assertEquals(detailTvResponse.name, tvEntities.data?.name)
    }
}