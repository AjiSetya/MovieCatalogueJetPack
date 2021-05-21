package com.blogsetyaaji.moviecatalogue.ui.detailtv

import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.data.Tv
import com.blogsetyaaji.moviecatalogue.utils.TvDummy

class DetailTvViewModel : ViewModel() {
    private val listDataTv: List<Tv> = TvDummy.generateDummyTv()
    var data: Tv? = null

    fun getTvByPosition(position: Int) {
        data = listDataTv[position]
    }
}