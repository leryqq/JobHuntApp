package com.example.jobhuntapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobhuntapp.MainActivity
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.ComplianceViewModel
import com.example.jobhuntapp.ViewModels.VacanciesViewModel
import com.example.jobhuntapp.adapters.home.RvVacanciesAdapter
import com.example.jobhuntapp.databinding.FragmentComplianceVacanciesBinding

class ComplianceVacanciesFragment : Fragment() {
    private lateinit var binding: FragmentComplianceVacanciesBinding
    private lateinit var vacanciesAdapter: RvVacanciesAdapter
    private val complianceViewModel: ComplianceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComplianceVacanciesBinding.inflate(layoutInflater, container, false)
        (activity as? MainActivity)?.setToolbarVisibility(false)
        val vacanciesViewModel: VacanciesViewModel = VacanciesViewModel(requireContext())

        setQuery()
        backClickSearchView()

        complianceViewModel.lookingNow.observe(viewLifecycleOwner) {
            binding.textViewComplianceVacancies.text = it
        }

        vacanciesAdapter = RvVacanciesAdapter(vacanciesViewModel)

        binding.recyclerViewVacancy.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = vacanciesAdapter
            isNestedScrollingEnabled = false
        }

        vacanciesViewModel.getVacancies().observe(viewLifecycleOwner) { vacancies ->
            val items: List<Any> = vacancies.map { it }
            vacanciesAdapter.items = items

            val complianceVacancies = "${items.size} ${getString(R.string.compliance_vacancies)}"
            complianceViewModel.setText(complianceVacancies)
        }

        vacanciesViewModel.buttonClickEvent.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Button clicked for item $it", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_complianceVacanciesFragment_to_vacancyFragment,
                args = Bundle().apply {
                    putString("vacancy_id", it)
                })
        }

        return binding.root
    }

    private fun setQuery() {
        arguments?.getString("search_query")?.let {
            binding.editTextSearchBar.setQuery(it, false)
        }
    }

    private fun backClickSearchView() {
        binding.imageButtonBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}