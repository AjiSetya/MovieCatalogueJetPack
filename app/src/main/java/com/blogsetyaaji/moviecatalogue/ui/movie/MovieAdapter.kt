package com.blogsetyaaji.moviecatalogue.ui.movie

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
import com.blogsetyaaji.moviecatalogue.data.source.local.entity.MovieEntity
import com.blogsetyaaji.moviecatalogue.databinding.ItemListMovieBinding
import com.blogsetyaaji.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movieEntity = ArrayList<MovieEntity?>()

    fun setMovies(movies: List<MovieEntity?>?) {
        if (movies == null) return
        this.movieEntity.clear()
        this.movieEntity.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemListMovieBinding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemListMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieEntity[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieEntity.size

    class MovieViewHolder(private val binding: ItemListMovieBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(movie: MovieEntity?) {
            with(binding) {
                titleMovie.text = movie?.title
                ratingMovie.rating = movie?.voteAverage?.div(2)?.toFloat()!!
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)

                    val posterPair = Pair<View, String>(posterMovie, "img_movie_trasition")
                    val containerPair =
                        Pair<View, String>(containerItemMovie, "container_transition")
                    val titlePair = Pair<View, String>(titleMovie, "title_movie_transition")
                    val ratingPair = Pair<View, String>(ratingMovie, "rating_movie_transition")

                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        posterPair, containerPair, titlePair, ratingPair
                    )

//                    Toast.makeText(itemView.context, movie.id.toString(), Toast.LENGTH_LONG).show()

                    itemView.context.startActivity(intent, options.toBundle())
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(posterMovie)
            }
        }
    }
}