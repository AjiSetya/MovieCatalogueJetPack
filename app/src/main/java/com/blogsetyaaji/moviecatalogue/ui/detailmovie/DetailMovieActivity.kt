package com.blogsetyaaji.moviecatalogue.ui.detailmovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.databinding.ActivityDetailMovieBinding
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "DetailMovie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailMovieBinding: ActivityDetailMovieBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        val positiion: Int = intent.getIntExtra(EXTRA_MOVIE, 0)
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]

        viewModel.getMovieByPosition(positiion)

        activityDetailMovieBinding.lifecycleOwner = this
        activityDetailMovieBinding.viewModelDetailMovie = viewModel

        detail_movie_back.setOnClickListener { supportFinishAfterTransition() }
        detail_movie_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hi, I have interesting movie for you!\n\n" +
                        "${viewModel.data?.name}\n\nRating: ${viewModel.data?.rating}\n" +
                        "Storyline:\n${viewModel.data?.overview}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}