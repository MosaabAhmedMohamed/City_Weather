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
    Log.d("mapTAG", "mapToUIModel: ${getWeekday(day)}")
    return WeatherUiModel(
        getWeekday(day),
        minDegree.toString(),
        maxDegree.toString(),
        rain,
        OpenWeatherMapSotrageUrl.plus(weatherIc.plus(".png"))
    )
}