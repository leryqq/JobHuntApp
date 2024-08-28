package com.example.jobhuntapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BadgeViewModel : ViewModel() {
    private val _badgeNumber = MutableLiveData<Int>().apply { value = 0 }
    val badgeNumber: LiveData<Int> get() = _badgeNumber

    fun setBadgeNumber(number: Int) {
        _badgeNumber.value = number
    }
}