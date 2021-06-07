package com.blogsetyaaji.moviecatalogue.ui.tv

import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.utils.TvDummy

class TvViewModel: ViewModel() {
    fun getTv(): List<TvEntity> = TvDummy.generateDummyTv()

}