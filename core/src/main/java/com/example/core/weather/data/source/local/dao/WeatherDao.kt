package com.example.core.weather.data.source.local.dao

import androidx.room.*
import com.example.core.weather.data.source.local.model.Weather
import io.reactivex.Flowable

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(weathers: List<Weather>)

    @Query("select * from Weather WHERE cityName = :cityName")
    fun getForecast(cityName: String): Flowable<List<Weather>>

    @Query("delete from Weather WHERE cityName = :city")
    fun deleteAllEntriesForCity(city: String)

    @Transaction
    fun cacheForecast(city: String, weathers: List<Weather>) {
        deleteAllEntriesForCity(city)
        insertForecast(weathers)
    }
}