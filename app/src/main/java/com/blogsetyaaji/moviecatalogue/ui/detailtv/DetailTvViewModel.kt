package com.blogsetyaaji.moviecatalogue.ui.detailtv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.networking.ContentRepository

class DetailTvViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    var data: LiveData<DetailTvResponse?>? = null

    fun getDetailTv(id: Int?): LiveData<DetailTvResponse?>? {
        data = contentRepository.getDetailTv(id, BuildConfig.MYAPI_KEY)
        return data
    }
}