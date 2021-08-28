package com.example.core.weather.presentation.viewstate

import com.example.core.weather.presentation.uimodel.WeatherUiModel

sealed class WeatherViewState {

    object Loading : WeatherViewState()
    object isLoadFromCache : WeatherViewState()
    object emptyState : WeatherViewState()
    data class onSuccess(val result: List<WeatherUiModel>) : WeatherViewState()
    data class onError(val error: Throwable) : WeatherViewState()
}