package com.blogsetyaaji.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogsetyaaji.moviecatalogue.R
import kotlinx.android.synthetic.main.fragment_tv.*


class TvFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvViewModel::class.java]
            val tv = viewModel.getTv()

            val tvAdapter = TvAdapter()
            tvAdapter.setTv(tv)

            rv_tv.layoutManager = LinearLayoutManager(context)
            rv_tv.setHasFixedSize(true)
            rv_tv.adapter = tvAdapter
        }
    }
}