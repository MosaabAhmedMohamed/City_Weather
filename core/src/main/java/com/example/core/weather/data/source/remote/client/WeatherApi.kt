package com.example.core.weather.data.source.remote.client

import com.example.core.base.data.model.BaseModel
import com.example.core.weather.data.source.remote.model.WeatherRemote
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApi {

    @POST("/data/2.5/forecast")
    fun getWeather(
        @Query("q") city: String,
        @Query("APPID") apiKey: String,
        @Query("cnt") numOfDays: String,
        @Query("units") unit: String
    ): Single<BaseModel<List<WeatherRemote>>>

}