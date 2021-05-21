package com.blogsetyaaji.moviecatalogue.ui.tv

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.data.Tv
import com.blogsetyaaji.moviecatalogue.databinding.ItemListTvBinding
import com.blogsetyaaji.moviecatalogue.ui.detailtv.DetailTvActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvAdapter : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    private var listTv = ArrayList<Tv>()

    fun setTv(tv: List<Tv>?) {
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemListTvBinding =
            ItemListTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemListTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv, position)
    }

    override fun getItemCount(): Int = listTv.size

    class TvViewHolder(private val binding: ItemListTvBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(tv: Tv, position: Int) {
            with(binding) {
                titleTv.text = tv.name
                ratingTv.rating = tv.rating.div(2).toFloat()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, position)

                    val posterPair = Pair<View, String>(posterTv, "img_tv_trasition")
                    val containerPair =
                        Pair<View, String>(containerItemTv, "container_transition")
                    val titlePair = Pair<View, String>(titleTv, "title_tv_transition")
                    val ratingPair = Pair<View, String>(ratingTv, "rating_tv_transition")

                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        posterPair, containerPair, titlePair, ratingPair
                    )

                    itemView.context.startActivity(intent, options.toBundle())
                    Toast.makeText(itemView.context, tv.name, Toast.LENGTH_SHORT).show()
                }
                Glide.with(itemView.context)
                    .load(tv.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(posterTv)
            }
        }
    }
}
