package com.example.cityweather.presentation.ui.base.ext

import android.view.View


fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visible(visibility_: Boolean) {
    visibility = if (visibility_)
        View.VISIBLE
    else
        View.GONE
}