package com.example.core.weather.data.source.remote

import com.example.core.weather.data.source.remote.client.WeatherApi
import com.example.core.weather.data.source.remote.model.WeatherRemote
import com.example.core.weather.domain.entity.param.WeatherRequestPrams
import io.reactivex.Single
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val weatherApi: WeatherApi) {

    fun getCityWeather(
        prams: WeatherRequestPrams
    ): Single<List<WeatherRemote>> {
        return weatherApi.getWeather(prams.city, prams.apiKey, prams.numberOfDays, prams.metric)
            .map {
                it.list
            }
    }


}