package com.blogsetyaaji.moviecatalogue.ui.favorite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.databinding.FragmentFavoriteTvBinding
import com.blogsetyaaji.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(context)
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]

            val favoriteTVAdapter = FavoriteTVAdapter()

            binding.pgFavTv.visibility = View.VISIBLE

            viewModel.getFavoriteTv().observe(viewLifecycleOwner, { favMovies ->
                if (favMovies != null) {
                    binding.pgFavTv.visibility = View.GONE
                    favoriteTVAdapter.setTv(favMovies)
                    favoriteTVAdapter.notifyDataSetChanged()
                } else {
                    binding.pgFavTv.visibility = View.GONE
                    Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                }
            })

            with(binding.rvFavTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTVAdapter
            }
        }
    }
}