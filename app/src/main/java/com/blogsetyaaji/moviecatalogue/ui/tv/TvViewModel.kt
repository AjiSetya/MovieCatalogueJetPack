package com.blogsetyaaji.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.blogsetyaaji.moviecatalogue.BuildConfig
import com.blogsetyaaji.moviecatalogue.data.ContentRepository
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.vo.Resource

class TvViewModel(private val contentRepository: ContentRepository) : ViewModel() {
    fun getTv(): LiveData<Resource<PagedList<TvEntity>>> =
        contentRepository.getAllTv(BuildConfig.MYAPI_KEY)
}