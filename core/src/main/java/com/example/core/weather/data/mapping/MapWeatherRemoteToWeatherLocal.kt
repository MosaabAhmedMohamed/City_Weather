package com.example.core.weather.data.mapping

import com.example.core.weather.data.source.local.model.Weather
import com.example.core.weather.data.source.remote.model.WeatherRemote

fun List<WeatherRemote>.mapToLocalModel(): MutableList<Weather> {
    val uiModels = mutableListOf<Weather>()
    forEach {
        uiModels.add(it.mapToLocalModel())
    }
    return uiModels
}

fun WeatherRemote.mapToLocalModel(): Weather {
    return Weather(
        day = dt,
        minDegree = main.temp_min,
        maxDegree = main.temp_max,
        rain = "",
        weatherIc = weather[0].icon
    )
}