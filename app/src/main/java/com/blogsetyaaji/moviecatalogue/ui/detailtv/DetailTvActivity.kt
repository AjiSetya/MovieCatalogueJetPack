package com.blogsetyaaji.moviecatalogue.ui.detailtv

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.movie.DetailMovieResponse
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.databinding.ActivityDetailTvBinding
import com.blogsetyaaji.moviecatalogue.viewmodel.ViewModelFactory
import com.blogsetyaaji.moviecatalogue.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTvActivity : AppCompatActivity() {
    private var isFavorite: Boolean = false
    private lateinit var detailTv: DetailTvResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDetailTvBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_tv)

        val itemTv: TvEntity? = intent.getParcelableExtra(EXTRA_TV)

        val factory = ViewModelFactory.getInstance(applicationContext)
        val viewModel = ViewModelProvider(this, factory)[DetailTvViewModel::class.java]

        viewModel.getDetailTv(itemTv?.id).observe(this, { tv ->
            if (tv != null) {
                when (tv.status) {
                    Status.LOADING -> binding.pgDetailTv.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        detailTv = tv.data!!

                        binding.pgDetailTv.visibility = View.GONE

                        binding.detailTvShare.setOnClickListener {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT, "Hi, I have interesting movie for you!\n\n" +
                                            "${tv.data?.name}\n\nRating: ${tv.data?.voteAverage}\n" +
                                            "Storyline:\n${tv.data?.overview}"
                                )
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            startActivity(shareIntent)
                        }
                    }
                    Status.ERROR -> {
                        binding.pgDetailTv.visibility = View.GONE
                        Toast.makeText(applicationContext, R.string.error, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })

        viewModel.getFavoriteTv(itemTv?.id).observe(this, {
            isFavorite = if (it?.id == itemTv?.id) {
                binding.detailTvFavorite.setImageResource(R.drawable.ic_baseline_favorite_selected)
                true
            } else {
                binding.detailTvFavorite.setImageResource(R.drawable.ic_baseline_favorite_unselected)
                false
            }
        })

        binding.detailTvFavorite.setOnClickListener {
            if (this::detailTv.isInitialized) {
                if (isFavorite) {
                    viewModel.deleteTvFromFavorite(detailTv)
                } else {
                    viewModel.addTvToFavorite(detailTv)
                }
            } else {
                Toast.makeText(this, getString(R.string.please_wait), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + itemTv?.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imgDetailTv)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + itemTv?.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.bgDetailTv)

        binding.textNameTv.text = itemTv?.name
        binding.ratingDetailTv.rating =
            itemTv?.voteAverage?.div(2)?.toFloat()!!

        binding.detailTvBack.setOnClickListener {
            supportFinishAfterTransition()
        }

        binding.run {
            lifecycleOwner = this@DetailTvActivity
            viewModelDetailTv = viewModel
        }
    }

    companion object {
        const val EXTRA_TV = "DetailMovie"
    }
}