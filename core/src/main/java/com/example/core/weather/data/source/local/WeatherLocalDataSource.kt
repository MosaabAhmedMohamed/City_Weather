package com.example.core.weather.data.source.local

import com.example.core.weather.data.source.local.dao.WeatherDao
import com.example.core.weather.data.source.local.model.Weather
import io.reactivex.Flowable
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(private val weatherDao: WeatherDao) {

    fun getForecast(cityName: String): Flowable<List<Weather>> {
        return weatherDao.getForecast(cityName)
    }

    fun cacheForecast(city: String,weathers: List<Weather>) = weatherDao.cacheForecast(city,weathers)

}