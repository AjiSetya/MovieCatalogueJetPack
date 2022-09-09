package com.blogsetyaaji.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.blogsetyaaji.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(context)
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            val favoriteMovieAdapter = FavoriteMovieAdapter()

            binding.pgFavMovie.visibility = View.VISIBLE

            viewModel.getFavoriteMovie().observe(viewLifecycleOwner, { favMovies ->
                if (favMovies != null) {
                    binding.pgFavMovie.visibility = View.GONE
                    favoriteMovieAdapter.setMovies(favMovies)
                    favoriteMovieAdapter.notifyDataSetChanged()
                } else {
                    binding.pgFavMovie.visibility = View.GONE
                    Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                }
            })

            with(binding.rvFavMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMovieAdapter
            }
        }
    }
}