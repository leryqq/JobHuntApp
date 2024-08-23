package com.example.jobhuntapp.ViewModels

import android.app.usage.UsageEvents.Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobhuntapp.R

class AuthViewModel: ViewModel() {

    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String> get() = _inputText

    private val _isInputValid = MutableLiveData<Boolean>()
    val isInputValid: LiveData<Boolean> get() = _isInputValid

    private val _isContinueButtonEnabled = MutableLiveData<Boolean>()
    val isContinueButtonEnabled: LiveData<Boolean> get() = _isContinueButtonEnabled

    private val _navigateToAuthCode = MutableLiveData<Boolean>()
    val navigateToAuthCode: LiveData<Boolean> get() = _navigateToAuthCode

    private val _showToastEvent = MutableLiveData<String>()
    val showToastEvent: LiveData<String> get() = _showToastEvent

    fun onTextChanged(text: String) {
        _inputText.value = text
        _isInputValid.value = isValidEmail(text)
        _isContinueButtonEnabled.value = isInputValid.value == true
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun onContinueButtonClick() {
        _navigateToAuthCode.value = true
    }
    fun navigationComplete() {
        _navigateToAuthCode.value = false // Сбрасываем значение после навигации
    }

    fun onLoginWithPassButtonClicked(message: String) {
        _showToastEvent.value = message
    }

    fun onSearchEmployeeButtonClicked(message: String) {
        _showToastEvent.value = message
    }
}