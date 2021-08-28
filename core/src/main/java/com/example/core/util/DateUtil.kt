package com.example.core.util

import android.util.Log
import java.util.*

fun getWeekday(unixTime: Long?): String {


    Log.d("mapTAG", "mapToUIModel: ${unixTime}")

    val date = Date(unixTime!!)
    val c = Calendar.getInstance()
    c.time = date

    /*System.out.println(c[Calendar.DAY_OF_WEEK])*/

    return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)

}