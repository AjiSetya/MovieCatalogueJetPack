package com.blogsetyaaji.moviecatalogue.ui.detailtv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.blogsetyaaji.moviecatalogue.databinding.ActivityDetailTvBinding
import com.blogsetyaaji.moviecatalogue.ui.detailmovie.DetailMovieViewModel
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.detail_movie_back
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTvActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV = "DetailMovie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailTvBinding: ActivityDetailTvBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_tv)

        val positiion: Int = intent.getIntExtra(EXTRA_TV, 0)
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailTvViewModel::class.java]

        viewModel.getTvByPosition(positiion)

        activityDetailTvBinding.lifecycleOwner = this
        activityDetailTvBinding.viewModelDetailTv = viewModel

        detail_tv_back.setOnClickListener { supportFinishAfterTransition() }
        detail_tv_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, "Hi, I have interesting TV series for you!\n\n" +
                        "${viewModel.data?.name}\n\nRating: ${viewModel.data?.rating}\n" +
                        "Storyline:\n${viewModel.data?.overview}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}