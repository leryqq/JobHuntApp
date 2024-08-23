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
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

        setupTextWatchers(viewModel)
        onConfirmButtonClick()


        return binding.root
    }

    private fun setupTextWatchers(viewModel: AuthCodeViewModel) {
        viewModel.editText1Value.observe(requireActivity(), Observer { text ->
            // Обновляем UI или выполняем действия, если нужно
        })

        viewModel.editText2Value.observe(requireActivity(), Observer { text ->
            // Обновляем UI или выполняем действия, если нужно
        })

        viewModel.editText3Value.observe(requireActivity(), Observer { text ->
            // Обновляем UI или выполняем действия, если нужно
        })

        viewModel.editText4Value.observe(requireActivity(), Observer { text ->
            // Обновляем UI или выполняем действия, если нужно
        })

        binding.editTextCodeSymbol1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Обновляем LiveData в ViewModel
                viewModel.editText1Value.value = s.toString()
                // Переключаем фокус на следующий EditText если поле заполнено
                if (s != null && s.length == 1) {
                    binding.editTextCodeSymbol2.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.editTextCodeSymbol2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.editText2Value.value = s.toString()
                if (s != null && s.length == 1) {
                    binding.editTextCodeSymbol3.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.editTextCodeSymbol3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.editText3Value.value = s.toString()
                if (s != null && s.length == 1) {
                    binding.editTextCodeSymbol4.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.editTextCodeSymbol4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.editText3Value.value = s.toString()
                if (s != null && s.length == 1) {
                    binding.buttonConfirm.callOnClick()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun onConfirmButtonClick() {
        binding.buttonConfirm.setOnClickListener {
            viewModel.onConfirmButtonClicked()
        }

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