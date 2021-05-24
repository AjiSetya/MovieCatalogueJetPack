package com.blogsetyaaji.moviecatalogue.ui.tv

import com.blogsetyaaji.moviecatalogue.utils.TvDummy
import junit.framework.TestCase

class TvViewModelTest : TestCase() {
    private lateinit var viewModel: TvViewModel

    override fun setUp() {
        super.setUp()
        viewModel = TvViewModel()
    }

    fun testGetTv() {
        val tv = viewModel.getTv()
        assertNotNull(tv)
        assertEquals(TvDummy.generateDummyTv().size, tv.size)
    }
}