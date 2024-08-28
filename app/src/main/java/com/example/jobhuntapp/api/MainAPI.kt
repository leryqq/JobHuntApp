package com.example.jobhuntapp.api

import com.example.jobhuntapp.dataclasses.Offers
import com.example.jobhuntapp.dataclasses.Vacancies
import retrofit2.Call
import retrofit2.http.GET

interface MainAPI {
    @GET("offers/")
    suspend fun getOffers(): List<Offers>
    @GET("vacancies/")
    suspend fun getVacancies(): List<Vacancies>
}