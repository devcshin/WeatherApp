package com.devc.fitpettest.usecase

import com.devc.fitpettest.data.model.WeatherResult
import com.devc.fitpettest.repository.MainRepository
import com.devc.fitpettest.data.request.WeatherParam
import retrofit2.Response
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repository: MainRepository
)  {

    suspend operator fun invoke(param: WeatherParam): Response<WeatherResult> {
        return repository.getWeather(param)
    }
}