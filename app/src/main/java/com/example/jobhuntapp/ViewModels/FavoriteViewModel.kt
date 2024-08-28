package com.example.jobhuntapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel : ViewModel() {
    private val _favoriteCount = MutableLiveData<String>()
    val favoriteCount: LiveData<String> get() = _favoriteCount

    fun updateData(newData: Int) {
        val favoriteCountText = getFavoritePhrase(newData)
        _favoriteCount.value = favoriteCountText
    }

    private fun getFavoritePhrase(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "$count вакансия"
            count % 10 in 2..4 && (count % 100 !in 12..14) -> "$count вакансии"
            else -> "$count вакансий"
        }
    }
}