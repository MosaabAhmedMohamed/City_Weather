package com.example.core.weather.data.repository

import com.example.core.weather.data.mapping.mapToLocalModel
import com.example.core.weather.data.source.local.WeatherLocalDataSource
import com.example.core.weather.data.source.local.model.Weather
import com.example.core.weather.data.source.remote.WeatherRemoteDataSource
import com.example.core.weather.domain.entity.param.WeatherRequestPrams
import com.example.core.weather.domain.repository.WeatherRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {


    override fun getForecast(cityName: String): Flowable<List<Weather>> {
        return weatherLocalDataSource.getForecast(cityName)
    }

    override fun cacheForecast(city: String,forecast: List<Weather>) {
        weatherLocalDataSource.cacheForecast(city,forecast)
    }

    override fun getCityWeather(
        prams: WeatherRequestPrams
    ): Single<List<Weather>> {
        return weatherRemoteDataSource.getCityWeather(prams)
            .map {
                it.mapToLocalModel()
            }
    }

}