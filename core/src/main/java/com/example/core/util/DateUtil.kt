package com.example.core.util

import java.text.SimpleDateFormat
import java.util.*

fun getWeekday(date_: Long): String {

    val sdf = SimpleDateFormat("yyyy-MM-dd kk:mm", Locale.ENGLISH)
    val dateStr = sdf.format(Date(date_ * 1000))
    val time = SimpleDateFormat("kk:mm", Locale.getDefault())
        .format(sdf.parse(dateStr))
    return SimpleDateFormat("EEEE", Locale.getDefault())
        .format(sdf.parse(dateStr))
        .plus(" ($time) ") ?: "Unknown"
}