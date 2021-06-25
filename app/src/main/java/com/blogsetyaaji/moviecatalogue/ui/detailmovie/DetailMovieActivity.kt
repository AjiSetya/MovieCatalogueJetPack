package com.blogsetyaaji.moviecatalogue.ui.detailmovie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.blogsetyaaji.moviecatalogue.viewmodel.ViewModelFactory
import com.blogsetyaaji.moviecatalogue.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailMovieBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        val itemMovie: MovieEntity? = intent.getParcelableExtra(EXTRA_MOVIE)

        val factory = ViewModelFactory.getInstance(applicationContext)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        viewModel.getDetailMovie(itemMovie?.id)?.observe(this, { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> binding.pgDetailMovie.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.pgDetailMovie.visibility = View.GONE
                        binding.detailMovieBack.setOnClickListener {
                            supportFinishAfterTransition()
                        }
                        binding.detailMovieShare.setOnClickListener {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT, "Hi, I have interesting movie for you!\n\n" +
                                            "${movies.data?.title}\n\nRating: ${movies.data?.voteAverage}\n" +
                                            "Storyline:\n${movies.data?.overview}"
                                )
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            startActivity(shareIntent)
                        }
                    }
                    Status.ERROR -> {
                        binding.pgDetailMovie.visibility = View.GONE
                        Toast.makeText(applicationContext, R.string.error, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        })

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + itemMovie?.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imgDetailMovie)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + itemMovie?.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.bgDetailMovie)

        binding.textNameMovie.text = itemMovie?.title
        binding.ratingDetailMovie.rating =
            itemMovie?.voteAverage?.div(2)?.toFloat()!!

        binding.run {
            lifecycleOwner = this@DetailMovieActivity
            viewModelDetailMovie = viewModel
        }
    }

    companion object {
        const val EXTRA_MOVIE = "DetailMovie"
    }
}