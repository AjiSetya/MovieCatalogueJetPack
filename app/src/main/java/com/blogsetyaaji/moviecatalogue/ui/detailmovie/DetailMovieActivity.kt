package com.blogsetyaaji.moviecatalogue.ui.detailmovie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.blogsetyaaji.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailMovieBinding: ActivityDetailMovieBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        val itemMovie = intent.getParcelableExtra(EXTRA_MOVIE) as MovieEntity?

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        activityDetailMovieBinding.pgDetailMovie.visibility = View.VISIBLE

        viewModel.getDetailMovie(itemMovie?.id)?.observe(this, { movies ->
            activityDetailMovieBinding.pgDetailMovie.visibility = View.GONE
            activityDetailMovieBinding.detailMovieBack.setOnClickListener {
                supportFinishAfterTransition()
            }
            activityDetailMovieBinding.detailMovieShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT, "Hi, I have interesting movie for you!\n\n" +
                                "${movies?.title}\n\nRating: ${movies?.voteAverage}\n" +
                                "Storyline:\n${movies?.overview}"
                    )
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        })

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + itemMovie?.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(activityDetailMovieBinding.imgDetailMovie)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + itemMovie?.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(activityDetailMovieBinding.bgDetailMovie)

        activityDetailMovieBinding.textNameMovie.text = itemMovie?.title
        activityDetailMovieBinding.ratingDetailMovie.rating =
            itemMovie?.voteAverage?.div(2)?.toFloat()!!

        activityDetailMovieBinding.run {
            lifecycleOwner = this@DetailMovieActivity
            viewModelDetailMovie = viewModel
        }

    }

    companion object {
        const val EXTRA_MOVIE = "DetailMovie"
    }
}