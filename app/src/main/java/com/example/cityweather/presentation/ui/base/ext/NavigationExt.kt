package com.example.cityweather.presentation.ui.base.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cityweather.presentation.ui.weather.ui.activity.WeatherActivity


fun Fragment.navigate(action: Int, args: Bundle? = null) {
    findNavController().navigate(action, args)
}

fun Activity.navigateToWeather(finishCurrent: Boolean = true) {
    startActivity(Intent(this, WeatherActivity::class.java))
    if (finishCurrent)
        finish()
}
