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
import com.example.jobhuntapp.ViewModels.FavoriteViewModel
import com.example.jobhuntapp.ViewModels.VacanciesViewModel
import com.example.jobhuntapp.adapters.home.RvVacanciesAdapter
import com.example.jobhuntapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val badgeViewModel: BadgeViewModel by activityViewModels()
    private lateinit var vacanciesAdapter: RvVacanciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        (activity as? MainActivity)?.setToolbarVisibility(false)
        val vacanciesViewModel: VacanciesViewModel = VacanciesViewModel(requireContext())
        vacanciesAdapter = RvVacanciesAdapter(vacanciesViewModel)

        binding.recyclerViewVacancy.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = vacanciesAdapter
            isNestedScrollingEnabled = false
        }

        vacanciesViewModel.getFavoriteVacancies().observe(viewLifecycleOwner) { vacancies ->
            val items: List<Any> = vacancies.map { it }
            vacanciesAdapter.items = items

            favoriteViewModel.updateData(vacancies.size)
            badgeViewModel.setBadgeNumber(vacancies.size)
        }

        vacanciesViewModel.buttonClickEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(
                R.id.action_favoriteFragment_to_vacancyFragment,
                args = Bundle().apply {
                    putString("vacancy_id", it)
                })
        }

        favoriteViewModel.favoriteCount.observe(viewLifecycleOwner) {
            binding.textViewFavoriteCount.text = it
        }

        return binding.root
    }
}