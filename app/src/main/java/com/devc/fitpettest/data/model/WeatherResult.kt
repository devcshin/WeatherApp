package com.devc.fitpettest.data.model

data class WeatherResult(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItem>,
    val message: Long
)