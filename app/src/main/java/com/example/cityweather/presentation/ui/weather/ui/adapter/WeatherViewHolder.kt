package com.example.cityweather.presentation.ui.weather.ui.adapter

import android.util.Log
import android.view.View
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.cityweather.databinding.ItemWeatherBinding
import com.example.cityweather.presentation.ui.base.BaseViewHolder
import com.example.core.weather.presentation.uimodel.WeatherUiModel

class WeatherViewHolder(itemView: View) : BaseViewHolder<WeatherUiModel?>(itemView) {
    private val binding = ItemWeatherBinding.bind(itemView)

    override fun onBind(item: WeatherUiModel?) {
        binding.tvDayName.text = item?.day
        binding.tvDegree.text = "${item?.minDegree} / ${item?.maxDegree}"

        Log.d("onBindTAG", "onBind: ${item?.weatherIc}")

        binding.imgWeather.load(item?.weatherIc) {
           /* crossfade(true)
            transformations(RoundedCornersTransformation(10F))*/
        }
    }
}