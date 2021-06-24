package com.blogsetyaaji.moviecatalogue.networking

import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig {
    companion object{
        fun provideHttpLoggingInterceptor() = run {
            HttpLoggingInterceptor().apply {
                apply {
                    if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
                }
            }
        }

        fun getApiService(): ApiInterface {
            val client = OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}