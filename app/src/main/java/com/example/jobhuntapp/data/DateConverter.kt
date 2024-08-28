package com.example.jobhuntapp.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter {
    fun formatDate(dateString: String): String {
        // Создаём экземпляр SimpleDateFormat для парсинга
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Создаём экземпляр SimpleDateFormat для форматирования
        val outputFormat = SimpleDateFormat("dd MMMM", Locale("ru"))

        // Парсим строку в Date
        val date: Date? = inputFormat.parse(dateString)

        // Форматируем Date в нужный формат
        return date?.let { outputFormat.format(it) } ?: "Некорректная дата"
    }
}