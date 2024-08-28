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
import androidx.navigation.navOptions
import com.example.jobhuntapp.MainActivity
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.AuthCodeViewModel
import com.example.jobhuntapp.databinding.FragmentAuthCodeBinding

class AuthCodeFragment : Fragment() {

    private lateinit var binding: FragmentAuthCodeBinding
    private val viewModel: AuthCodeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthCodeBinding.inflate(layoutInflater, container, false)
        (activity as? MainActivity)?.setToolbarVisibility(false)
        setEmailText()
        setupTextWatchers(viewModel)
        onConfirmButtonClick()


        return binding.root
    }

    private fun setEmailText() {
        arguments?.getString("email")?.let {
            viewModel.setEmail(it)
        }

        viewModel.email.observe(viewLifecycleOwner) { value ->
            val text = "${getString(R.string.send_code_to)} $value"
            binding.textViewSendCodeTo.text = text
        }
    }

    private fun setupTextWatchers(viewModel: AuthCodeViewModel) {
        viewModel.editText1Value.observe(viewLifecycleOwner) { text ->
            isConfirmButtonEnabled()
        }

        viewModel.editText2Value.observe(viewLifecycleOwner) { text ->
            isConfirmButtonEnabled()
        }

        viewModel.editText3Value.observe(viewLifecycleOwner) { text ->
            isConfirmButtonEnabled()
        }

        viewModel.editText4Value.observe(viewLifecycleOwner) { text ->
            isConfirmButtonEnabled()
        }

        binding.editTextCodeSymbol1.requestFocus()
        binding.editTextCodeSymbol1.hint = null

        binding.editTextCodeSymbol1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateEditText1Value(s.toString())
                if (s != null && s.length == 1) {
                    binding.editTextCodeSymbol2.requestFocus()
                    binding.editTextCodeSymbol2.hint = null

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.editTextCodeSymbol2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateEditText2Value(s.toString())
                if (s != null && s.length == 1) {
                    binding.editTextCodeSymbol3.requestFocus()
                    binding.editTextCodeSymbol3.hint = null

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.editTextCodeSymbol3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateEditText3Value(s.toString())
                if (s != null && s.length == 1) {
                    binding.editTextCodeSymbol4.requestFocus()
                    binding.editTextCodeSymbol4.hint = null

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.editTextCodeSymbol4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateEditText4Value(s.toString())
                if (s != null && s.length == 1) {
                    binding.buttonConfirm.callOnClick()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun isConfirmButtonEnabled() {
        viewModel.isConfirmButtonEnabled.observe(viewLifecycleOwner) {
            binding.buttonConfirm.isEnabled = it
            if (it) {
                binding.buttonConfirm.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.blue_active)
            } else {
                binding.buttonConfirm.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.blue_disabled)
            }
        }
    }

    private fun onConfirmButtonClick() {
        binding.buttonConfirm.setOnClickListener {
            viewModel.onConfirmButtonClicked()
        }

        viewModel.navigateToHome.observe(viewLifecycleOwner) {shouldNavigate ->
            if (shouldNavigate == true) {
                findNavController().navigate(R.id.action_authCodeFragment_to_homeFragment, null, navOptions {popUpTo(R.id.authCodeFragment) {inclusive = true} })
                viewModel.navigationComplete()
            }
        }

        viewModel.showToastEvent.observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}