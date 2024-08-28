package com.example.jobhuntapp.data

import android.content.Context
import com.example.jobhuntapp.R
import com.example.jobhuntapp.dataclasses.Offers
import com.example.jobhuntapp.dataclasses.Vacancies
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class JsonData(val context: Context) {
    private val gson = Gson()

    private fun jsonConverter(): JsonObject {
        val inputStream = context.resources.openRawResource(R.raw.mock_response)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        return gson.fromJson(jsonString, JsonObject::class.java)
    }

    fun getOffersFromJson(): List<Offers> {
        val jsonObject = jsonConverter()
        val offersArray = jsonObject.getAsJsonArray("offers")
        val type = object : TypeToken<List<Offers>>() {}.type

        return gson.fromJson(offersArray, type)
    }

    fun getVacanciesFromJson(): List<Vacancies> {
        val jsonObject = jsonConverter()
        val vacanciesArray = jsonObject.getAsJsonArray("vacancies")
        val type = object : TypeToken<List<Vacancies>>() {}.type

        return gson.fromJson(vacanciesArray.toString(), type)
    }

    fun getVacancyByIdFromJson(vacancyId: String): Vacancies? {
        val jsonObject = jsonConverter()
        val vacanciesArray = jsonObject.getAsJsonArray("vacancies")
        val type = object : TypeToken<List<Vacancies>>() {}.type
        val vacancyObject: List<Vacancies> = gson.fromJson(vacanciesArray.toString(), type)

        return vacancyObject.find { it.id == vacancyId }
    }

    fun getFavoriteVacanciesFromJson(): List<Vacancies> {
        val jsonObject = jsonConverter()
        val vacanciesArray = jsonObject.getAsJsonArray("vacancies")
        val type = object : TypeToken<List<Vacancies>>() {}.type
        val vacancyObject: List<Vacancies> = gson.fromJson(vacanciesArray.toString(), type)

        return vacancyObject.filter { it.isFavorite }
    }
}