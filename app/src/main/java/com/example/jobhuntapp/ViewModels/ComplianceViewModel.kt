package com.example.jobhuntapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComplianceViewModel : ViewModel() {
    private val _lookingNow = MutableLiveData<String>()
    val lookingNow: LiveData<String> get() = _lookingNow

    fun setText(newText: String) {
        _lookingNow.value = newText
    }
}