package com.devc.fitpettest.ui.main

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devc.fitpettest.common.Constants
import com.devc.fitpettest.data.model.ViewData
import com.devc.fitpettest.databinding.ForecastItemBinding


class ForecastViewHolder(private val binding: ForecastItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(data: ViewData) {
        binding.tvDate.text = data.date

        Glide.with(binding.ivWeatherIcon)
            .load(Constants.IMG_BASE_URL+"${data.weatherIcon}"+Constants.IMG_SUFFIX)
            .centerInside()
            .into(binding.ivWeatherIcon)

        binding.tvWeather.text = data.weather
        binding.tvMin.text = "min : ${data.minTemp} ℃"
        binding.tvMax.text = "max : ${data.maxTemp} ℃"

    }
}