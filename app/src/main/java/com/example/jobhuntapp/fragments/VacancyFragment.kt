package com.example.jobhuntapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobhuntapp.MainActivity
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.VacanciesViewModel
import com.example.jobhuntapp.ViewModels.VacancyViewModel
import com.example.jobhuntapp.adapters.vacancy.RvQuestionsAdapter
import com.example.jobhuntapp.databinding.FragmentVacancyBinding
import com.example.jobhuntapp.dataclasses.Vacancies
import com.example.jobhuntapp.fragments.bottomsheet.BottomSheetApplyVacancyFragment

class VacancyFragment : Fragment() {
    private lateinit var binding: FragmentVacancyBinding
    private val viewModel: VacancyViewModel by activityViewModels()
    private lateinit var questionsAdapter: RvQuestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacancyBinding.inflate(layoutInflater, container, false)
        (activity as? MainActivity)?.setToolbarVisibility(true)
        val vacanciesViewModel: VacanciesViewModel = VacanciesViewModel(requireContext())
        questionsAdapter = RvQuestionsAdapter()

        binding.recyclerViewQuestions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = questionsAdapter
            isNestedScrollingEnabled = false
        }

        arguments?.getString("vacancy_id")?.let {
            vacanciesViewModel.getVacancyById(it).observe(requireActivity(), Observer { vacancy ->
                vacancy?.let { it1 ->
                    updateUI(it1)
                    questionsAdapter.items = it1.questions
                    viewModel.setFavorite(it1.isFavorite)
                }
            })
        }

        binding.buttonSearchEmployee.setOnClickListener {
            childFragmentManager.let { BottomSheetApplyVacancyFragment().show(it, BottomSheetApplyVacancyFragment.APPLY_TAG) }
        }

        return binding.root
    }

    private fun updateUI(vacancy: Vacancies) {
        binding.textViewVacancyTitle.text = vacancy.title
        binding.textViewVacancySalary.text = vacancy.salary.full
        val experienceText = "${getString(R.string.required_experience)} ${vacancy.experience.text}"
        binding.textViewExperience.text = experienceText
        val schedulesText = vacancy.schedules.joinToString(separator = ",")
        binding.textViewSchedules.text = schedulesText
        if (vacancy.appliedNumber != null) {
            val appliedNumberText = "${vacancy.appliedNumber} ${getString(R.string.people_applied)}"
            binding.textViewAppliedNumber.text = appliedNumberText
        } else {
            binding.cardViewAppliedNumber.visibility = View.GONE
        }
        if (vacancy.lookingNumber != null) {
            val lookingNowText = "${vacancy.lookingNumber} ${getString(R.string.looking_now1)}"
            binding.textViewLookingNow.text = lookingNowText
        } else {
            binding.cardViewLookingNow.visibility = View.GONE
        }
        binding.textViewCompanyName.text = vacancy.company
        val companyAddressText = "${vacancy.address.town}, ${vacancy.address.street}, ${vacancy.address.house}"
        binding.textViewCompanyAddress.text = companyAddressText
        binding.textViewCompanyDescription.text = vacancy.description
        binding.textViewResponsibilitiesDescription.text = vacancy.responsibilities
    }
}