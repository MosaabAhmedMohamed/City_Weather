package com.example.core.weather.domain.usecase

import com.example.core.weather.data.source.local.model.Weather
import com.example.core.weather.domain.repository.WeatherRepository
import io.reactivex.Flowable
import javax.inject.Inject

class WeatherLocalCacheUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun getForecast(cityName: String): Flowable<List<Weather>> {
        return weatherRepository.getForecast(cityName)
    }

    fun cacheForecast(city: String, forecast: List<Weather>) {
        addCityNameForItems(city, forecast)
        weatherRepository.cacheForecast(city, forecast)
    }

    private fun addCityNameForItems(city: String, forecast: List<Weather>) {
        forecast.forEach {
            it.cityName = city
        }
    }

}