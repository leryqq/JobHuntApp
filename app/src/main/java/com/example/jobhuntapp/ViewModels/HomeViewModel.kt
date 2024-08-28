package com.example.jobhuntapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _searchQuery

    private val _vacanciesQuantity = MutableLiveData<String>()
    val vacanciesQuantity: LiveData<String> get() = _vacanciesQuantity

    fun updateQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun updateButton(newQuantity: Int) {
        _vacanciesQuantity.value = getPhrase(newQuantity)
    }

    private fun getPhrase(count: Int): String {
        return when {
            count % 10 == 1 && count % 100 != 11 -> "Еще $count вакансия"
            count % 10 in 2..4 && (count % 100 !in 12..14) -> "Еще $count вакансии"
            else -> "Еще $count вакансий"
        }
    }
}