package com.example.cityweather.di

import com.example.core.weather.di.WeatherModule
import com.example.cityweather.presentation.ui.weather.di.WeatherFragmentBuilderModule
import com.example.cityweather.presentation.ui.weather.ui.activity.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [WeatherModule::class, WeatherFragmentBuilderModule::class]
    )
    abstract fun binTutorialsActivity(): WeatherActivity

}