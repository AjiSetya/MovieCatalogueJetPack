package com.blogsetyaaji.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogsetyaaji.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val movies = viewModel.getMovies()

            val movieAdapter = MovieAdapter()
            movieAdapter.setMovies(movies)

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)
            rv_movie.adapter = movieAdapter
        }
    }
}