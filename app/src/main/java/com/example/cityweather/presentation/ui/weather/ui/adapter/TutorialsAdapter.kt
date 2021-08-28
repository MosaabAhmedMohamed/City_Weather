package com.example.cityweather.presentation.ui.weather.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cityweather.R
import com.example.core.weather.presentation.uimodel.WeatherUiModel


class TutorialsAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var weatherModels: MutableList<WeatherUiModel?> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_weather, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = weatherModels[position]
        handleBinding(holder, item)
    }

    private fun handleBinding(
        holder: RecyclerView.ViewHolder,
        item: WeatherUiModel?
    ) {
        (holder as WeatherViewHolder).onBind(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(result: List<WeatherUiModel>) {
        weatherModels = result.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = weatherModels.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        weatherModels.clear()
        notifyDataSetChanged()
    }


}