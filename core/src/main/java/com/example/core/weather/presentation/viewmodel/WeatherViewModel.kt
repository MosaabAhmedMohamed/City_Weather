package com.example.core.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.presentation.BaseViewModel
import com.example.core.weather.domain.usecase.GetRemoteWeatherUseCase
import com.example.core.weather.domain.usecase.WeatherLocalCacheUseCase
import com.example.core.weather.presentation.viewstate.WeatherViewState
import com.example.core.util.SchedulerProvider
import com.example.core.weather.data.source.local.model.Weather
import com.example.core.weather.domain.entity.param.WeatherRequestPrams
import com.example.core.weather.presentation.mapping.mapToUIModel
import io.reactivex.Flowable
import io.reactivex.rxkotlin.addTo
import java.io.IOException
import java.net.ConnectException
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val weatherLocalCacheUseCase: WeatherLocalCacheUseCase,
    private val getRemoteWeatherUseCase: GetRemoteWeatherUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {


    private val weatherViewStateLDPrivate by lazy { MutableLiveData<WeatherViewState>() }
    val weatherViewStateLD: LiveData<WeatherViewState> get() = weatherViewStateLDPrivate

    fun getForecast(city: String) {
        getRemoteWeatherUseCase.getCityWeather(WeatherRequestPrams(city))
            .subscribeOn(schedulerProvider.io())
            .doOnSubscribe {
                weatherViewStateLDPrivate.postValue(WeatherViewState.Loading)
            }
            .doOnError { onConnectionException(it) }
            .onErrorReturn { emptyList() }
            .flatMap { return@flatMap handleLocalCache(city, it) }
            .observeOn(schedulerProvider.ui())
            .subscribe({
                if (it.isNotEmpty())
                    weatherViewStateLDPrivate.value = WeatherViewState.onSuccess(it.mapToUIModel())
                else
                    weatherViewStateLDPrivate.value = WeatherViewState.emptyState
            }, {

            })
            .addTo(compositeDisposable)
    }

    private fun onConnectionException(it: Throwable) {
        if (it is ConnectException || it is IOException) {
            weatherViewStateLDPrivate.postValue(WeatherViewState.isLoadFromCache)
        } else {
            weatherViewStateLDPrivate.postValue(WeatherViewState.onError(it))
        }
    }

    private fun handleLocalCache(
        city: String,
        forecast: List<Weather>
    ): Flowable<List<Weather>> {
        return if (forecast.isEmpty()) {
            weatherLocalCacheUseCase.getForecast(city).flatMap {
                Flowable.just(it)
            }
        } else {
            weatherLocalCacheUseCase.cacheForecast(city, forecast)
            Flowable.just(forecast)
        }
    }

}

