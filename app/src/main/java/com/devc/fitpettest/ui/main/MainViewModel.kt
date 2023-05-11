package com.devc.fitpettest.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devc.fitpettest.data.model.WeatherItem
import com.devc.fitpettest.data.model.WeatherResult
import com.devc.fitpettest.common.Constants
import com.devc.fitpettest.data.model.ViewData
import com.devc.fitpettest.data.request.WeatherParam
import com.devc.fitpettest.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: WeatherUseCase
) : BaseViewModel() {

    companion object {
        private const val TOADY = "Today"
        private const val TOMORROW = "Tomorrow"
        private const val ITEM = "Item"
        private const val TITLE = "Title"
        private const val METRIC = "metric"
    }

    //보통은 서버에서 받아오는게 맞습니다
    private val locationList: List<String> = listOf("Seoul", "London", "Chicago")

    private var _liveData = MutableLiveData<WeatherResult>()

    private var _forecastList: MutableList<WeatherResult?> = mutableListOf()

    private val _viewData = MutableLiveData<List<ViewData>>()
    val viewData: LiveData<List<ViewData>> get() = _viewData

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadDataUseCase(locationList)
    }

    private fun loadDataUseCase(location: List<String>) {
        viewModelScope.launch {
            for (item in location) {
                val data = useCase(WeatherParam(Constants.API_KEY, item, METRIC))
                when (data.isSuccessful) {
                    true -> {
                        _liveData.postValue(data.body())
                        if (data.body() != null) {
                            _forecastList.add(toWeeklyForecast(data.body()!!))
                        }
                        Log.d("Success Result :","${data.body()}")
                    }
                    else -> {
                        Log.d("Error Result :","${data.body()}")
                    }
                }
            }
            Log.d("Success Append :","$_forecastList")
            _viewData.value = toViewData(_forecastList)
            Log.d("Success Append List :","$_viewData")
            _isLoading.value = false
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun toWeeklyForecast(result: WeatherResult): WeatherResult {
        val dateFormatString = "yyyyMMdd"
        var dateBox = "0"

        val newList: MutableList<WeatherItem> = mutableListOf()
        for (item in result.list) {
            val date = Date(item.dt * 1000L)
            val simpleDateFormat = SimpleDateFormat(dateFormatString)
            val today = simpleDateFormat.format(date)
            if (dateBox < today && newList.size < 5) {
                newList.add(item)
            }
            dateBox = simpleDateFormat.format(date)
        }
        return WeatherResult(
            city = result.city,
            cod = result.cod,
            cnt = newList.size,
            list = newList,
            message = result.message
        )
    }

    private fun toViewData(data: List<WeatherResult?>): List<ViewData> {
        val dateFormatString = "EEE dd MMM"

        val returnData: MutableList<ViewData> = mutableListOf()
        for (item in data) {
            returnData.add(ViewData(type = TITLE, item?.city?.name, null, null, null, null, null))
            for ((index, list) in item?.list!!.withIndex()) {
                val date = Date(list.dt * 1000L)
                val simpleDateFormat = SimpleDateFormat(dateFormatString, Locale("en"))
                var dateFormat = simpleDateFormat.format(date)
                if (index == 0) {
                    dateFormat = TOADY
                } else if (index == 1) {
                    dateFormat = TOMORROW
                }
                returnData.add(
                    ViewData(
                        type = ITEM,
                        region = null,
                        date = dateFormat,
                        weather = list.weather[0].main,
                        weatherIcon = list.weather[0].icon,
                        list.main.temp_min.toString(),
                        list.main.temp_max.toString()
                    )
                )
            }
        }
        return returnData
    }
}