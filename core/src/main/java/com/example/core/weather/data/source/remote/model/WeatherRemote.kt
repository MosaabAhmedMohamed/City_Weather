package com.example.core.weather.data.source.remote.model

data class WeatherRemote(val dt : Long,
                    val main : Main,
                    val weather : List<Weather>,
                    val clouds : Clouds,
                    val wind : Wind,
                    val visibility : Int,
                    val pop : Int,
                    val sys : Sys,
                    val dt_txt : String)