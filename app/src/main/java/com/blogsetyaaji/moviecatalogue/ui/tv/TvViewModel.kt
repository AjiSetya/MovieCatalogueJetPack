package com.blogsetyaaji.moviecatalogue.ui.tv

import androidx.lifecycle.ViewModel
import com.blogsetyaaji.moviecatalogue.data.Tv
import com.blogsetyaaji.moviecatalogue.utils.TvDummy

class TvViewModel: ViewModel() {
    fun getTv(): List<Tv> = TvDummy.generateDummyTv()

}