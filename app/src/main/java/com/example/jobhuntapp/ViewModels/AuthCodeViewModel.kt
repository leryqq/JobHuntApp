package com.example.jobhuntapp.ViewModels

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText

class AuthCodeViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _editText1Value = MutableLiveData<String>()
    val editText1Value: LiveData<String> get() = _editText1Value

    private val _editText2Value = MutableLiveData<String>()
    val editText2Value: LiveData<String> get() = _editText2Value

    private val _editText3Value = MutableLiveData<String>()
    val editText3Value: LiveData<String> get() = _editText3Value

    private val _editText4Value = MutableLiveData<String>()
    val editText4Value: LiveData<String> get() = _editText4Value

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> get() = _navigateToHome

    fun setEmail(value: String) {
        _email.value = value
    }

    val isConfirmButtonEnabled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        // Начальное значение
        value = false

        // Обновляем состояние кнопки, когда изменяется любое из полей
        addSource(_editText1Value) { updateButtonState() }
        addSource(_editText2Value) { updateButtonState() }
        addSource(_editText3Value) { updateButtonState() }
        addSource(_editText4Value) { updateButtonState() }
    }

    private fun updateButtonState() {
        isConfirmButtonEnabled as MediatorLiveData
        isConfirmButtonEnabled.value = !(
                editText1Value.value.isNullOrEmpty() ||
                        editText2Value.value.isNullOrEmpty() ||
                        editText3Value.value.isNullOrEmpty() ||
                        editText4Value.value.isNullOrEmpty()
                )
    }

    // Методы для обновления значений полей
    fun updateEditText1Value(value: String) {
        _editText1Value.value = value
    }

    fun updateEditText2Value(value: String) {
        _editText2Value.value = value
    }

    fun updateEditText3Value(value: String) {
        _editText3Value.value = value
    }

    fun updateEditText4Value(value: String) {
        _editText4Value.value = value
    }

    private val _showToastEvent = MutableLiveData<String>()
    val showToastEvent: LiveData<String> get() = _showToastEvent

    fun onConfirmButtonClicked() {
        // Устанавливаем значение LiveData для отображения Toast
        //_showToastEvent.value = "Button clicked!"
        _navigateToHome.value = true
    }

    fun navigationComplete() {
        _navigateToHome.value = false // Сбрасываем значение после навигации
    }
}