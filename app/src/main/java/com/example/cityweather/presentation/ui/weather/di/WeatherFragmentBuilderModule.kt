package com.example.cityweather.presentation.ui.weather.di

import com.example.cityweather.presentation.ui.weather.ui.fragment.ForecastListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideForecastListFragment(): ForecastListFragment
}