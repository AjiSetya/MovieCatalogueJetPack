package com.blogsetyaaji.moviecatalogue.ui.favorite.tv

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.TvEntity
import com.blogsetyaaji.moviecatalogue.data.source.remote.response.detail.tv.DetailTvResponse
import com.blogsetyaaji.moviecatalogue.databinding.ItemListBinding
import com.blogsetyaaji.moviecatalogue.ui.detailtv.DetailTvActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoriteTVAdapter: RecyclerView.Adapter<FavoriteTVAdapter.FavTvViewHolder>() {
    private var listTv = ArrayList<DetailTvResponse?>()

    fun setTv(tv: List<DetailTvResponse?>?) {
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvViewHolder {
        val itemListTvBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTvViewHolder(itemListTvBinding)
    }

    override fun onBindViewHolder(holder: FavTvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int = listTv.size

    class FavTvViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(tv: DetailTvResponse?) {
            with(binding) {
                titleItem.text = tv?.name
                ratingItem.rating = tv?.voteAverage?.div(2)?.toFloat()!!
                itemView.setOnClickListener {
                    val tvEntity = TvEntity(
                        tv.firstAirDate, tv.overview, tv.originalLanguage, null, tv.posterPath,
                        tv.originCountry as List<String>?, tv.backdropPath, tv.originalName, tv.popularity, tv.voteAverage,
                        tv.name, tv.id!!, tv.voteCount
                    )

                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, tvEntity)

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
}
