package com.example.jobhuntapp.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.jobhuntapp.R
import com.example.jobhuntapp.ViewModels.AuthCodeViewModel
import com.example.jobhuntapp.databinding.FragmentAuthCodeBinding

class AuthCodeFragment : Fragment() {

    private lateinit var binding: FragmentAuthCodeBinding
    private val viewModel: AuthCodeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthCodeBinding.inflate(layoutInflater, container, false)

        setEmailText()
        setupTextWatchers(viewModel)
        onConfirmButtonClick()


        return binding.root
    }

    private fun setEmailText() {
        arguments?.getString("email")?.let {
            viewModel.setEmail(it)
        }

        viewModel.email.observe(requireActivity(), Observer { value ->
            val text = "${getString(R.string.send_code_to)} $value"
            binding.textViewSendCodeTo.text = text
        })
    }

    private fun setupTextWatchers(viewModel: AuthCodeViewModel) {
        viewModel.editText1Value.observe(requireActivity(), Observer { text ->
            // Обновляем UI или выполняем действия, если нужно
            isConfirmButtonEnabled()
        })

        viewModel.editText2Value.observe(requireActivity(), Observer { text ->
            // Обновляем UI или выполняем действия, если нужно
            isConfirmButtonEnabled()
        })

        viewModel.editText3Value.observe(requireActivity(), Observer { text ->
            // Обновляем UI или выполняем действия, если нужно
            isConfirmButtonEnabled()
        })

        viewModel.editText4Value.observe(requireActivity(), Observer { text ->
            // Обновляем UI или выполняем действия, если нужно
            isConfirmButtonEnabled()
        })

        binding.editTextCodeSymbol1.requestFocus()
        binding.editTextCodeSymbol1.hint = null

        binding.editTextCodeSymbol1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Обновляем LiveData в ViewModel
                viewModel.updateEditText1Value(s.toString())
                // Переключаем фокус на следующий EditText если поле заполнено
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
        viewModel.isConfirmButtonEnabled.observe(requireActivity(), Observer {
            binding.buttonConfirm.isEnabled = it
            if (it) {
                binding.buttonConfirm.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.blue_active)
            } else {
                binding.buttonConfirm.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.blue_disabled)
            }
        })
    }

    private fun onConfirmButtonClick() {
        binding.buttonConfirm.setOnClickListener {
            viewModel.onConfirmButtonClicked()
        }

        viewModel.navigateToHome.observe(requireActivity(), Observer {shouldNavigate ->
            if (shouldNavigate == true) {
                findNavController().navigate(R.id.action_authCodeFragment_to_homeFragment, null, navOptions {popUpTo(R.id.authCodeFragment) {inclusive = true} })
                viewModel.navigationComplete()
            }
        })

        viewModel.showToastEvent.observe(requireActivity(), Observer { message ->
            message?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthCodeFragment()
    }
}