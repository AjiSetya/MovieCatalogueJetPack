package com.blogsetyaaji.moviecatalogue.ui.tv

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.databinding.ItemListBinding
import com.blogsetyaaji.moviecatalogue.ui.detailtv.DetailTvActivity
import com.blogsetyaaji.moviecatalogue.ui.movie.MovieAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvAdapter : PagedListAdapter<TvEntity, TvAdapter.TvViewHolder>(DIFF_CALLBACK) {
    private var listTv = ArrayList<TvEntity?>()

    fun setTv(tv: List<TvEntity?>?) {
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemListTvBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemListTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    class TvViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(tv: TvEntity?) {
            with(binding) {
                titleItem.text = tv?.name
                ratingItem.rating = tv?.voteAverage?.div(2)?.toFloat()!!
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, tv)

                    val posterPair = Pair<View, String>(posterItem, "img_tv_trasition")
                    val containerPair =
                        Pair<View, String>(containerItem, "container_transition")
                    val titlePair = Pair<View, String>(titleItem, "title_tv_transition")
                    val ratingPair = Pair<View, String>(ratingItem, "rating_tv_transition")

                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        posterPair, containerPair, titlePair, ratingPair
                    )

                    itemView.context.startActivity(intent, options.toBundle())
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + tv.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(posterItem)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean =
                oldItem == newItem

        }
    }
}
