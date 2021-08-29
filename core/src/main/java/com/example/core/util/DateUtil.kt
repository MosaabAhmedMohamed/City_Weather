package com.example.core.util

import java.text.SimpleDateFormat
import java.util.*

fun getWeekday(date_: Long): String {

    val sdf = SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH)
    val dateStr = sdf.format(Date(date_ * 1000))
    return SimpleDateFormat("EEEE", Locale.getDefault()).format(sdf.parse(dateStr))?:"Unknown"
}