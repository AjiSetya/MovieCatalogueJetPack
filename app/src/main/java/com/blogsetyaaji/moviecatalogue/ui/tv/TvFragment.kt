package com.blogsetyaaji.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogsetyaaji.moviecatalogue.databinding.FragmentTvBinding
import com.blogsetyaaji.moviecatalogue.viewmodel.ViewModelFactory


class TvFragment : Fragment() {
    private lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val tvAdapter = TvAdapter()
            fragmentTvBinding.pgTv.visibility = View.VISIBLE

            viewModel.getTv().observe(viewLifecycleOwner, { tv ->
                fragmentTvBinding.pgTv.visibility = View.GONE
                tvAdapter.setTv(tv?.results)
                tvAdapter.notifyDataSetChanged()
            })

            with(fragmentTvBinding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }
}