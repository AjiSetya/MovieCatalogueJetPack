package com.blogsetyaaji.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.TvResponse
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository

class TvViewModel(private val contentRepository: ContentRepository): ViewModel() {
    fun getTv(): LiveData<TvResponse?> {
        return contentRepository.getAllTv(BuildConfig.MYAPI_KEY)
    }
}