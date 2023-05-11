package com.devc.fitpettest.data.model

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Double,
    val sunrise: Double,
    val sunset: Double,
    val timezone: Long
)