package com.example.core.weather.presentation.uimodel

data class WeatherUiModel(
    var day: String?,
    val minDegree: String?,
    var maxDegree: String?,
    var weatherIc :String? = null
)
