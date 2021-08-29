package com.example.core.weather.presentation.mapping

import android.util.Log
import com.example.core.util.data.APIConst.Companion.OpenWeatherMapSotrageUrl
import com.example.core.util.getWeekday
import com.example.core.weather.data.source.local.model.Weather
import com.example.core.weather.presentation.uimodel.WeatherUiModel

fun List<Weather>.mapToUIModel(): MutableList<WeatherUiModel> {
    val uiModels = mutableListOf<WeatherUiModel>()
    forEach {
        uiModels.add(it.mapToUIModel())
    }
    return uiModels
}

fun Weather.mapToUIModel(): WeatherUiModel {
    return WeatherUiModel(
        day?.let { getWeekday(it) },
        minDegree?.toInt().toString(),
        maxDegree?.toInt().toString(),
        OpenWeatherMapSotrageUrl.plus(weatherIc.plus(".png"))
    )
}