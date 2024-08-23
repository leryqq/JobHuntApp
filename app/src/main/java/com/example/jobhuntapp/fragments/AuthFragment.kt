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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.AuthViewModel
import com.example.jobhuntapp.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

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
                viewModel.onTextChanged(s.toString()) // Передаем текст в ViewModel
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun observeViewModel() {
        viewModel.isInputValid.observe(viewLifecycleOwner, Observer { isValid ->
            if (isValid == false) {
                binding.textInputLayoutEmail.helperText = getString(R.string.wrong_email)
            } else {
                binding.textInputLayoutEmail.helperText = null
            }
        })
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
                findNavController().navigate(R.id.action_authFragment_to_authCodeFragment)
                viewModel.navigationComplete()
            }
        }

        viewModel.showToastEvent.observe(requireActivity(), Observer {message ->
            message?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}