package com.example.core.weather.domain.repository

import com.example.core.weather.data.source.local.model.Weather
import com.example.core.weather.domain.entity.param.WeatherRequestPrams
import io.reactivex.Flowable
import io.reactivex.Single

interface WeatherRepository {

    fun getForecast(cityName: String): Flowable<List<Weather>>

    fun cacheForecast(city: String,forecast: List<Weather>)

    fun getCityWeather(
        prams: WeatherRequestPrams
    ): Single<List<Weather>>

}