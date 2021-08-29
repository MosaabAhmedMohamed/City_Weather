package com.example.core.weather.data.source.remote

import com.example.core.util.data.APIConst.Companion.CITY_NOT_FOUND
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
                if (it.code == CITY_NOT_FOUND) {
                    emptyList()
                } else
                    it.list
            }
    }


}