package com.example.core.weather.domain.usecase

import com.example.core.weather.data.source.local.model.Weather
import com.example.core.weather.domain.entity.param.WeatherRequestPrams
import com.example.core.weather.domain.repository.WeatherRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetRemoteWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun getCityWeather(
        prams: WeatherRequestPrams
    ): Flowable<List<Weather>> {
        return weatherRepository.getCityWeather(prams).toFlowable()
    }
}