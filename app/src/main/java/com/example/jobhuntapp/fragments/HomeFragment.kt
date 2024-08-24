package com.example.jobhuntapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.HomeViewModel
import com.example.jobhuntapp.adapters.home.RvRecommendsAdapter
import com.example.jobhuntapp.databinding.FragmentHomeBinding
import com.example.jobhuntapp.dataclasses.OffersImageItem
import com.example.jobhuntapp.dataclasses.OffersTitleTextItem

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.recyclerViewRecommends.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapterRecommends = RvRecommendsAdapter()
        binding.recyclerViewRecommends.adapter = adapterRecommends

        val test = listOf(
            OffersTitleTextItem("Test 1"),
            OffersImageItem(R.drawable.ic_vacancy)
        )
        adapterRecommends.items = test

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}