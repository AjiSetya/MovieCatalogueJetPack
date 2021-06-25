package com.blogsetyaaji.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogsetyaaji.moviecatalogue.R
import com.blogsetyaaji.moviecatalogue.databinding.FragmentTvBinding
import com.blogsetyaaji.moviecatalogue.viewmodel.ViewModelFactory
import com.blogsetyaaji.moviecatalogue.vo.Status


class TvFragment : Fragment() {
    private lateinit var binding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(context)
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val tvAdapter = TvAdapter()


            viewModel.getTv().observe(viewLifecycleOwner, { tv ->
                binding.pgTv.visibility = View.GONE
                tvAdapter.setTv(tv.data)
                tvAdapter.notifyDataSetChanged()
                if (tv != null) {
                    when (tv.status) {
                        Status.LOADING -> binding.pgTv.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.pgTv.visibility = View.GONE
                            tvAdapter.setTv(tv.data)
                            tvAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.pgTv.visibility = View.GONE
                            Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }
}