package com.example.jobhuntapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.jobhuntapp.MainActivity
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.AuthViewModel
import com.example.jobhuntapp.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.setToolbarVisibility(false)
        setupEditText()
        observeViewModel()
        continueButtonEnable()
        buttonsClicks()

        return binding.root
    }

    private fun setupEditText() {
        binding.editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onTextChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.editTextEmail.setOnFocusChangeListener{ _, hasFocus ->
            if (hasFocus) {
                binding.editTextEmail.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            } else {
                binding.editTextEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mail_16, 0,0,0)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.isInputValid.observe(viewLifecycleOwner) { isValid ->
            if (isValid == false) {
                binding.textInputLayoutEmail.helperText = getString(R.string.wrong_email)
            } else {
                binding.textInputLayoutEmail.helperText = null
            }
        }
    }

    private fun continueButtonEnable() {
        viewModel.isContinueButtonEnabled.observe(viewLifecycleOwner) {
            binding.buttonContinue.isEnabled = it
            if (it) {
                binding.buttonContinue.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.blue_active)
            } else {
                binding.buttonContinue.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.blue_disabled)
            }
        }
    }

    private fun buttonsClicks() {
        binding.buttonContinue.setOnClickListener {
            viewModel.onContinueButtonClick()
        }

        binding.buttonLoginWithPass.setOnClickListener {
            viewModel.onLoginWithPassButtonClicked(getString(R.string.login_with_pass))
        }

        binding.buttonSearchEmployee.setOnClickListener {
            viewModel.onSearchEmployeeButtonClicked(getString(R.string.i_search_employee))
        }

        viewModel.navigateToAuthCode.observe(viewLifecycleOwner) {shouldNavigate ->
            if (shouldNavigate == true) {
                val bundle = Bundle()
                val email = binding.editTextEmail.editableText.toString()
                bundle.putString("email", email)
                findNavController().navigate(R.id.action_authFragment_to_authCodeFragment, bundle)
                viewModel.navigationComplete()
            }
        }

        viewModel.showToastEvent.observe(viewLifecycleOwner) {message ->
            message?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}