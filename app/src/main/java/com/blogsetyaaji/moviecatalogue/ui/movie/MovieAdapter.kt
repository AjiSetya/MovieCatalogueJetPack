package com.blogsetyaaji.moviecatalogue.ui.movie

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
import com.blogsetyaaji.moviecatalogue.databinding.ItemListBinding
import com.blogsetyaaji.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    private var movieEntity = ArrayList<MovieEntity?>()

    fun setMovies(movies: List<MovieEntity?>?) {
        if (movies == null) return
        this.movieEntity.clear()
        this.movieEntity.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemListMovieBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemListMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieEntity[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieEntity.size

    class MovieViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(movie: MovieEntity?) {
            with(binding) {
                titleItem.text = movie?.title
                ratingItem.rating = movie?.voteAverage?.div(2)?.toFloat()!!
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)

                    val posterPair = Pair<View, String>(posterItem, "img_movie_trasition")
                    val containerPair =
                        Pair<View, String>(containerItem, "container_transition")
                    val titlePair = Pair<View, String>(titleItem, "title_movie_transition")
                    val ratingPair = Pair<View, String>(ratingItem, "rating_movie_transition")

                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        posterPair, containerPair, titlePair, ratingPair
                    )

                    itemView.context.startActivity(intent, options.toBundle())
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(posterItem)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}