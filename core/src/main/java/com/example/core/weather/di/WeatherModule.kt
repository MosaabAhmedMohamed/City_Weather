package com.example.core.weather.di

import com.example.core.core.db.CityWeatherDatabase
import com.example.core.weather.data.repository.WeatherRepositoryImpl
import com.example.core.weather.data.source.local.WeatherLocalDataSource
import com.example.core.weather.data.source.remote.WeatherRemoteDataSource
import com.example.core.weather.data.source.remote.client.WeatherApi
import com.example.core.weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class WeatherModule {


    @Provides
    fun provideWeatherLocalDataSource(
        db: CityWeatherDatabase
    ): WeatherLocalDataSource =
        WeatherLocalDataSource(db.tutorialsDao())

    @Provides
    fun provideWeatherRemoteDataSource(
        api: WeatherApi
    ): WeatherRemoteDataSource =
        WeatherRemoteDataSource(api)

    @Provides
    fun provideWeatherRepository(
        weatherLocalDataSource: WeatherLocalDataSource,
        weatherRemoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository =
        WeatherRepositoryImpl(weatherLocalDataSource, weatherRemoteDataSource)


    @Provides
    fun provideWeatherApi(retrofit: Retrofit.Builder): WeatherApi {
        return retrofit
            .build()
            .create(WeatherApi::class.java)
    }

}