package com.example.core.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.presentation.BaseViewModel
import com.example.core.weather.domain.usecase.GetRemoteWeatherUseCase
import com.example.core.weather.domain.usecase.WeatherLocalCacheUseCase
import com.example.core.weather.presentation.viewstate.WeatherViewState
import com.example.core.util.SchedulerProvider
import com.example.core.util.data.APIConst.Companion.BAD_REQUEST
import com.example.core.util.data.APIConst.Companion.CITY_NOT_FOUND
import com.example.core.weather.data.source.local.model.Weather
import com.example.core.weather.domain.entity.param.WeatherRequestPrams
import com.example.core.weather.presentation.mapping.mapToUIModel
import io.reactivex.Flowable
import io.reactivex.rxkotlin.addTo
import retrofit2.HttpException
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
            .filter { city.isNotEmpty() }
            .doOnSubscribe {
                weatherViewStateLDPrivate.postValue(WeatherViewState.Loading)
            }
            .flatMap { return@flatMap addToLocalCache(city, it) }
            .observeOn(schedulerProvider.ui())
            .subscribe({
                if (it.isNotEmpty())
                    weatherViewStateLDPrivate.value =
                        WeatherViewState.onSuccess(it.mapToUIModel(), false)
                else
                    getFromLocalCache(city)
            }, {
                onException(it, city)
            })
            .addTo(compositeDisposable)
    }

    private fun onException(it: Throwable, city: String) {
        if (it is ConnectException || it is IOException) {
            getFromLocalCache(city)
        } else if (it is HttpException && it.code().toString() == CITY_NOT_FOUND
            || it is HttpException && it.code().toString() == BAD_REQUEST
        ) {
            weatherViewStateLDPrivate.postValue(WeatherViewState.emptyState)
        } else {
            weatherViewStateLDPrivate.postValue(WeatherViewState.onError(it))
        }
    }

    private fun addToLocalCache(
        city: String,
        forecast: List<Weather>
    ): Flowable<List<Weather>> {
        if (forecast.isNotEmpty())
            weatherLocalCacheUseCase.cacheForecast(city, forecast)
        return Flowable.just(forecast)
    }

    private fun getFromLocalCache(city: String) {
        weatherLocalCacheUseCase.getForecast(city)
            .filter { weatherViewStateLDPrivate.value !is WeatherViewState.onSuccess }
            .subscribeOn(schedulerProvider.io())
            .doOnSubscribe {
                weatherViewStateLDPrivate.postValue(WeatherViewState.Loading)
            }
            .observeOn(schedulerProvider.ui())
            .subscribe({
                if (it.isNotEmpty()) {
                    weatherViewStateLDPrivate.value =
                        WeatherViewState.onSuccess(it.mapToUIModel(), true)
                } else
                    weatherViewStateLDPrivate.value = WeatherViewState.emptyState
            }, {
                weatherViewStateLDPrivate.value = WeatherViewState.onError(it)
            })
            .addTo(compositeDisposable)
    }

}

