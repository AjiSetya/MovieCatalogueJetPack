package com.blogsetyaaji.moviecatalogue.ui.detailtv

import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.utils.TvDummy

class DetailTvViewModel : ViewModel() {
    private val listDataTv: List<TvEntity> = TvDummy.generateDummyTv()
    var data: TvEntity? = null

    fun getTvByPosition(position: Int) {
        data = listDataTv[position]
    }
}