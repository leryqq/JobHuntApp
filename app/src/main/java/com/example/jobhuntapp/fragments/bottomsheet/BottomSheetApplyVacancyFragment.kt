package com.example.jobhuntapp.fragments.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jobhuntapp.databinding.FragmentBottomSheetApplyVacancyBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetApplyVacancyFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetApplyVacancyBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetApplyVacancyBinding.inflate(layoutInflater, container, false)

        binding.buttonAddCoverLetter.setOnClickListener {
            binding.buttonAddCoverLetter.visibility = View.GONE
            binding.editTextCoverLetter.visibility = View.VISIBLE
        }

        binding.buttonApply.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = BottomSheetApplyVacancyFragment()
        const val APPLY_TAG = "BottomSheetFragment"
    }
}