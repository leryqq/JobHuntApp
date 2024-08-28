package com.example.jobhuntapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobhuntapp.MainActivity
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.BadgeViewModel
import com.example.jobhuntapp.ViewModels.HomeViewModel
import com.example.jobhuntapp.ViewModels.OffersViewModel
import com.example.jobhuntapp.ViewModels.VacanciesViewModel
import com.example.jobhuntapp.adapters.home.RvRecommendsAdapter
import com.example.jobhuntapp.adapters.home.RvVacanciesAdapter
import com.example.jobhuntapp.databinding.FragmentHomeBinding
import com.example.jobhuntapp.dataclasses.Vacancies

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val badgeViewModel: BadgeViewModel by activityViewModels()
    private lateinit var offersAdapter: RvRecommendsAdapter
    private lateinit var vacanciesAdapter: RvVacanciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        (activity as? MainActivity)?.setToolbarVisibility(false)
        val offersViewModel: OffersViewModel = OffersViewModel(requireContext())
        val vacanciesViewModel: VacanciesViewModel = VacanciesViewModel(requireContext())

        offersAdapter = RvRecommendsAdapter()
        binding.recyclerViewRecommends.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = offersAdapter
        }

        vacanciesAdapter = RvVacanciesAdapter(vacanciesViewModel)
        binding.recyclerViewVacancy.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = vacanciesAdapter
            isNestedScrollingEnabled = false
        }

        offersViewModel.getOffers().observe(viewLifecycleOwner) { offers ->
            // Преобразуем List<Offers> в List<Any>
            val items: List<Any> = offers.map { it }
            offersAdapter.items = items
        }

        vacanciesViewModel.getVacancies().observe(viewLifecycleOwner) { vacancies ->
            val items: List<Any> = vacancies.map { it }
            vacanciesAdapter.items = items.take(3)
            homeViewModel.updateButton(vacancies.size - 3)

            val favoriteItems: List<Vacancies> = items
                .filterIsInstance<Vacancies>()
                .filter { it.isFavorite }
            badgeViewModel.setBadgeNumber(favoriteItems.size)
        }

        vacanciesViewModel.buttonClickEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(
                R.id.action_homeFragment_to_vacancyFragment,
                args = Bundle().apply {
                    putString("vacancy_id", it)
                })
        }

        binding.editTextSearchBar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    homeViewModel.updateQuery(it)
                    openComplianceFragment()
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        homeViewModel.vacanciesQuantity.observe(viewLifecycleOwner) {
            binding.buttonMoreVacancies.text = it
        }

        binding.buttonMoreVacancies.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_complianceVacanciesFragment)
        }
        return binding.root
    }

    private fun openComplianceFragment() {
        findNavController().navigate(
            R.id.action_homeFragment_to_complianceVacanciesFragment,
            args = Bundle().apply {
                putString(
                    "search_query",
                    binding.editTextSearchBar.query.toString()
                )
            })
    }
}