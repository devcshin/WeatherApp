package com.devc.fitpettest.data.model

data class ViewData(
    val type: String,
    val region: String?,
    val date: String?,
    val weather: String?,
    val weatherIcon: String?,
    val minTemp: String?,
    val maxTemp: String?
)
