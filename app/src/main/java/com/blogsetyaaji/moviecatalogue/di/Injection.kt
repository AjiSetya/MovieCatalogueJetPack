package com.blogsetyaaji.moviecatalogue.di

import android.content.Context
import com.blogsetyaaji.moviecatalogue.data.source.RemoteDataSource
import com.blogsetyaaji.moviecatalogue.networking.ApiConfig
import com.blogsetyaaji.moviecatalogue.networking.ApiInterface
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository
import retrofit2.Retrofit

object Injection {
    fun provideRepository(context: Context): ContentRepository {
        val remoteRepository = RemoteDataSource.getInstance()
        return ContentRepository.getInstance(remoteRepository)
    }
}