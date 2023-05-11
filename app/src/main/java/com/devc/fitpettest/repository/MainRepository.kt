package com.devc.fitpettest.repository

import com.devc.fitpettest.data.request.WeatherParam
import com.devc.fitpettest.service.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getWeather(param: WeatherParam) =
        apiService.getWeather(param.appId, param.q, param.units)
}