package com.example.core.weather.domain.entity.param

import com.example.core.util.data.APIConst.Companion.API_KEY
import com.example.core.util.data.APIConst.Companion.NUMBER_OF_FORECAST_DAYS
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class WeatherRequestPrams(
    @SerializedName("q") var city: String,
    @SerializedName("APPID") var apiKey: String = API_KEY,
    @SerializedName("cnt") var numberOfDays: String = NUMBER_OF_FORECAST_DAYS,
    @SerializedName("units") var metric: String = "metric"
) : Serializable