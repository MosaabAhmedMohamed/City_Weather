package com.example.core.weather.presentation.viewstate

import com.example.core.weather.presentation.uimodel.WeatherUiModel

sealed class WeatherViewState {

    object Loading : WeatherViewState()
    object emptyState : WeatherViewState()
    data class onSuccess(val result: List<WeatherUiModel>, val isLoadFromCache: Boolean = false) :
        WeatherViewState()

    data class onError(val error: Throwable) : WeatherViewState()
}