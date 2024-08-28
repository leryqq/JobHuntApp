package com.example.jobhuntapp.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobhuntapp.data.JsonData
import com.example.jobhuntapp.clients.RetrofitClient
import com.example.jobhuntapp.dataclasses.Vacancies
import kotlinx.coroutines.launch

class VacanciesViewModel(context: Context) : ViewModel() {
    private val vacanciesLiveData: MutableLiveData<List<Vacancies>> = MutableLiveData()
    private val vacancyLiveData: MutableLiveData<Vacancies?> = MutableLiveData()
    private val vacanciesJsonData = JsonData(context)

    private val _buttonClickEvent = MutableLiveData<String>()
    val buttonClickEvent: LiveData<String> get() = _buttonClickEvent

    fun getVacancies(): LiveData<List<Vacancies>> {
        viewModelScope.launch {
            try {
                val apiService = RetrofitClient.instance
                val vacancies = vacanciesJsonData.getVacanciesFromJson() //apiService.getVacancies()
                vacanciesLiveData.value = vacancies
            } catch (e: Exception) {
                Log.e("VacanciesViewModel", "Error fetching vacancies: ${e.localizedMessage}")
            }
        }
        return vacanciesLiveData
    }

    fun getVacancyById(vacancyId: String): LiveData<Vacancies?> {
        viewModelScope.launch {
            try {
                val apiService = RetrofitClient.instance
                val vacancies = vacanciesJsonData.getVacancyByIdFromJson(vacancyId) //apiService.getVacancies()
                vacancyLiveData.value = vacancies
            } catch (e: Exception) {
                Log.e("VacanciesViewModel", "Error fetching vacancies: ${e.localizedMessage}")
            }
        }
        return vacancyLiveData
    }

    fun getFavoriteVacancies(): LiveData<List<Vacancies>> {
        viewModelScope.launch {
            try {
                val apiService = RetrofitClient.instance
                val vacancies = vacanciesJsonData.getFavoriteVacanciesFromJson() //apiService.getVacancies()
                vacanciesLiveData.value = vacancies
                Log.d("MyLog", "onCreateView: $vacancies")
            } catch (e: Exception) {
                Log.e("VacanciesViewModel", "Error fetching vacancies: ${e.localizedMessage}")
            }
        }
        return vacanciesLiveData
    }

    fun onButtonClick(itemId: String) {
        _buttonClickEvent.value = itemId
    }
}