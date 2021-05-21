package com.blogsetyaaji.moviecatalogue.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.ui.movie.MovieFragment
import com.blogsetyaaji.moviecatalogue.ui.tv.TvFragment

class SectionsPagerAdapter(private val mContext: Context, supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2
}
