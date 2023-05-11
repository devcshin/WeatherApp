package com.devc.fitpettest.service

import com.devc.fitpettest.data.model.WeatherResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast")
    suspend fun getWeather(
        @Query("appId") appId: String, @Query("q") q: String, @Query("units") units: String
    ): Response<WeatherResult>
}