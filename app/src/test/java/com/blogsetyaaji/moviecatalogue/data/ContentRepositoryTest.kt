package com.blogsetyaaji.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.RemoteDataSource
import com.blogsetyaaji.moviecatalogue.utils.DataDummy
import com.blogsetyaaji.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class ContentRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val contentRepository = FakeContentRepository(remote)

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTv = DataDummy.generateDummyTv()
    private val dummyDetailMovie = DataDummy.generateDummyDetailMovie()
    private val dummyDetailTv = DataDummy.generateDummyDetailTv()

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieCallback)
                .onAllMoviesReceived(dummyMovie)
            null
        }.`when`(remote).getMovies(eq(BuildConfig.MYAPI_KEY), any())

        val movieEntities = LiveDataTestUtil.getValue(
            contentRepository.getAllMovies(BuildConfig.MYAPI_KEY)
        )

        verify(remote).getMovies(eq(BuildConfig.MYAPI_KEY), any())
        assertNotNull(movieEntities)
        assertEquals(dummyMovie.results?.size, movieEntities?.results?.size)
    }

    @Test
    fun getAllTv(){
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(dummyTv)
            null
        }.`when`(remote).getTv(eq(BuildConfig.MYAPI_KEY), any())

        val tvEntities = LiveDataTestUtil.getValue(
            contentRepository.getAllTv(BuildConfig.MYAPI_KEY)
        )

        verify(remote).getTv(eq(BuildConfig.MYAPI_KEY), any())
        assertNotNull(tvEntities)
        assertEquals(dummyTv.results.size, tvEntities?.results?.size)
    }

    @Test
    fun getDetailMovie(){
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadDetailMovieCallback)
                .onAllMoviesReceived(dummyDetailMovie)
            null
        }.`when`(remote).getDetailMovies(eq(399566) ,eq(BuildConfig.MYAPI_KEY), any())

        val detailEntitiy = LiveDataTestUtil.getValue(
            contentRepository.getDetailMovie(399566, BuildConfig.MYAPI_KEY)
        )

        verify(remote).getDetailMovies(eq(399566), eq(BuildConfig.MYAPI_KEY), any())
        assertNotNull(detailEntitiy)
        assertEquals(dummyDetailMovie.title, detailEntitiy?.title)
    }

    @Test
    fun getDetailTv(){
        doAnswer { invocation ->
            (invocation.arguments[2] as RemoteDataSource.LoadDetailTvCallback)
                .onAllTvReceived(dummyDetailTv)
            null
        }.`when`(remote).getDetailTv(eq(60735) ,eq(BuildConfig.MYAPI_KEY), any())

        val detailEntitiy = LiveDataTestUtil.getValue(
            contentRepository.getDetailTv(60735, BuildConfig.MYAPI_KEY)
        )

        verify(remote).getDetailTv(eq(60735), eq(BuildConfig.MYAPI_KEY), any())
        assertNotNull(detailEntitiy)
        assertEquals(dummyDetailTv.name, detailEntitiy?.name)
    }
}