package com.blogsetyaaji.moviecatalogue.di

import com.blogsetyaaji.moviecatalogue.data.source.RemoteDataSource
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository

object Injection {
    fun provideRepository(): ContentRepository {
        val remoteRepository = RemoteDataSource.getInstance()
        return ContentRepository.getInstance(remoteRepository)
    }
}