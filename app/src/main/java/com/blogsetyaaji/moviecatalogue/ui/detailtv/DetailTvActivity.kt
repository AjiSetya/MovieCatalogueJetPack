package com.blogsetyaaji.moviecatalogue.ui.detailtv

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.databinding.ActivityDetailTvBinding
import com.blogsetyaaji.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_tv.*

class DetailTvActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailTvBinding: ActivityDetailTvBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail_tv)

        val itemTv = intent.getParcelableExtra(EXTRA_TV) as TvEntity?

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailTvViewModel::class.java]

        activityDetailTvBinding.pgDetailTv.visibility = View.VISIBLE

        viewModel.getDetailTv(itemTv?.id)?.observe(this, { movies ->
            activityDetailTvBinding.pgDetailTv.visibility = View.GONE
            activityDetailTvBinding.detailTvBack.setOnClickListener {
                supportFinishAfterTransition()
            }
            activityDetailTvBinding.detailTvShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT, "Hi, I have interesting movie for you!\n\n" +
                                "${movies?.name}\n\nRating: ${movies?.voteAverage}\n" +
                                "Storyline:\n${movies?.overview}"
                    )
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        })

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + itemTv?.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(activityDetailTvBinding.imgDetailTv)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + itemTv?.posterPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(activityDetailTvBinding.bgDetailTv)

        activityDetailTvBinding.textNameTv.text = itemTv?.name
        activityDetailTvBinding.ratingDetailTv.rating =
            itemTv?.voteAverage?.div(2)?.toFloat()!!

        activityDetailTvBinding.run {
            lifecycleOwner = this@DetailTvActivity
            viewModelDetailTv = viewModel
        }
    }

    companion object {
        const val EXTRA_TV = "DetailMovie"
    }
}