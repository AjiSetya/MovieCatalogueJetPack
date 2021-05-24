package com.blogsetyaaji.moviecatalogue.ui.detailtv

import com.blogsetyaaji.moviecatalogue.utils.TvDummy
import junit.framework.TestCase
import kotlin.random.Random

class DetailTvViewModelTest : TestCase() {
    private val randomNumber = Random.nextInt(0, TvDummy.generateDummyTv().size - 1)
    private lateinit var detailTvViewModel: DetailTvViewModel
    private val tvDummy = TvDummy.generateDummyTv()[randomNumber]

    override fun setUp() {
        super.setUp()
        detailTvViewModel = DetailTvViewModel()
    }

    fun testGetTvByPosition() {
        detailTvViewModel.getTvByPosition(randomNumber)
        val result = detailTvViewModel.data

        assertNotNull(result)
        assertEquals(tvDummy.name, result?.name)
        assertEquals(tvDummy.overview, result?.overview)
        assertEquals(tvDummy.poster, result?.poster)
        assertEquals(tvDummy.rating, result?.rating)
        assertEquals(tvDummy.relaseDate, result?.relaseDate)
        assertEquals(tvDummy.episode, result?.episode)
    }
}