package com.blogsetyaaji.moviecatalogue.di

import android.content.Context
import com.blogsetyaaji.moviecatalogue.data.source.remote.RemoteDataSource
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.data.source.local.LocalDataSource
import com.blogsetyaaji.moviecatalogue.data.source.local.room.ContentDatabase
import com.blogsetyaaji.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): ContentRepository {
        val database = ContentDatabase.getInstance(context)
        val remoteRepository = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.MovieDao(), database.TvDao())
        val appExecutors = AppExecutors()
        return ContentRepository.getInstance(remoteRepository, localDataSource, appExecutors)
    }
}