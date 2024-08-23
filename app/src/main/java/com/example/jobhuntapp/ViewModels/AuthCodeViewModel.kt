package com.example.jobhuntapp.ViewModels

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText

class AuthCodeViewModel: ViewModel() {
    val editText1Value = MutableLiveData<String>()
    val editText2Value = MutableLiveData<String>()
    val editText3Value = MutableLiveData<String>()
    val editText4Value = MutableLiveData<String>()

    private val _showToastEvent = MutableLiveData<String>()
    val showToastEvent: LiveData<String> get() = _showToastEvent

    fun onConfirmButtonClicked() {
        // Устанавливаем значение LiveData для отображения Toast
        _showToastEvent.value = "Button clicked!"
    }
}