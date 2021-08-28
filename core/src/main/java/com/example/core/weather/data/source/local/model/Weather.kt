package com.example.core.weather.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "Weather")
data class Weather(

    @Expose
    @ColumnInfo(name = "cityName")
    var cityName: String? = null,

    @Expose
    @ColumnInfo(name = "day")
    var day: Long?,

    @Expose
    @ColumnInfo(name = "minDegree")
    var minDegree: Double?,

    @Expose
    @ColumnInfo(name = "maxDegree")
    var maxDegree: Double?,

    @ColumnInfo(name = "rain")
    var rain: String?,

    @ColumnInfo(name = "weatherIc")
    var weatherIc: String? = null
){
    @Expose
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

}
