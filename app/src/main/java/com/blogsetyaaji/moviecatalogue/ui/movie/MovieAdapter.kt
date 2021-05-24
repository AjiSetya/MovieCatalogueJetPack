package com.blogsetyaaji.moviecatalogue.ui.movie

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.data.Movie
import com.blogsetyaaji.moviecatalogue.databinding.ItemListMovieBinding
import com.blogsetyaaji.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.core.util.Pair

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemListMovieBinding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemListMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie, position)
    }

    override fun getItemCount(): Int = listMovies.size

    class MovieViewHolder(private val binding: ItemListMovieBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(movie: Movie, position: Int) {
            with(binding) {
                titleMovie.text = movie.name
                ratingMovie.rating = movie.rating.div(2).toFloat()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, position)

                    val posterPair = Pair<View, String>(posterMovie, "img_movie_trasition")
                    val containerPair =
                        Pair<View, String>(containerItemMovie, "container_transition")
                    val titlePair = Pair<View, String>(titleMovie, "title_movie_transition")
                    val ratingPair = Pair<View, String>(ratingMovie, "rating_movie_transition")

                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        posterPair, containerPair, titlePair, ratingPair
                    )

                    itemView.context.startActivity(intent, options.toBundle())
                }
                Glide.with(itemView.context)
                    .load(movie.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(posterMovie)
            }
        }
    }
}