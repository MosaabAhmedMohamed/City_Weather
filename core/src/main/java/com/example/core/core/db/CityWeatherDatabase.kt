package com.example.core.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.weather.data.source.local.dao.WeatherDao
import com.example.core.weather.data.source.local.model.Weather


@Database(
    entities = [Weather::class],
    version = CityWeather_DATABASE_VERSION_NUMBER
)

abstract class CityWeatherDatabase : RoomDatabase() {

    abstract fun tutorialsDao(): WeatherDao

}
const val CityWeather_DATABASE_VERSION_NUMBER = 9

