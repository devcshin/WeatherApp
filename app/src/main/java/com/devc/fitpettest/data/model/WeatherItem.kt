package com.devc.fitpettest.data.model

data class WeatherItem (
    val clouds: Clouds,
    val dt: Long,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Double,
    val weather: List<Weather>,
    val wind: Wind
)