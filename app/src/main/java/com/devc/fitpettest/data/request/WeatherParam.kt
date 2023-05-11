package com.devc.fitpettest.data.request

data class WeatherParam(
    val appId : String,
    var q : String,
    var units: String,
)