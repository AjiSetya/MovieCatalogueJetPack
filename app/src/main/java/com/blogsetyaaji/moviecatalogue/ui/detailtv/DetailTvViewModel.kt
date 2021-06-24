package com.blogsetyaaji.moviecatalogue.ui.detailtv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.vo.Resource

class DetailTvViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    var data: LiveData<Resource<DetailTvResponse?>>? = null

    fun getDetailTv(id: Int?): LiveData<Resource<DetailTvResponse?>>? {
        data = contentRepository.getDetailTv(id, BuildConfig.MYAPI_KEY)
        return data
    }
}