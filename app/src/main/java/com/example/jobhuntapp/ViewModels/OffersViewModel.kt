package com.example.jobhuntapp.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobhuntapp.data.JsonData
import com.example.jobhuntapp.clients.RetrofitClient
import com.example.jobhuntapp.dataclasses.Offers
import kotlinx.coroutines.launch

class OffersViewModel(context: Context) : ViewModel() {
    private val offersLiveData: MutableLiveData<List<Offers>> = MutableLiveData()
    private val offersJsonData = JsonData(context)

    fun getOffers(): LiveData<List<Offers>> {
        viewModelScope.launch {
            try {
                val apiService = RetrofitClient.instance
                val offers = offersJsonData.getOffersFromJson()    /*original*//*apiService.getOffers()*/
                offersLiveData.value = offers
            } catch (e: Exception) {
                Log.e("OffersViewModel", "Error fetching offers: ${e.localizedMessage}")
            }
        }
        return offersLiveData
    }
}