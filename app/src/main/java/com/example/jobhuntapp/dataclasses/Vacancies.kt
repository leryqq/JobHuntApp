package com.example.jobhuntapp.dataclasses

import android.text.format.DateFormat

data class Vacancies(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: VacanciesAddress,
    val company: String,
    val experience: VacanciesExperience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: VacanciesSalary,
    val schedules: ArrayList<String>,
    val appliedNumber: Int?,
    val description: String,
    val responsibilities: String,
    val questions: ArrayList<String>
)
