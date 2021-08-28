package com.example.cityweather.presentation.ui.weather.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.example.cityweather.databinding.ActivityWeatherBinding
import com.example.cityweather.presentation.ui.base.BaseActivity
import com.example.core.core.di.ViewModelFactory
import com.example.core.weather.presentation.viewmodel.SearchViewModel
import javax.inject.Inject

class WeatherActivity : BaseActivity() {

    lateinit var binding: ActivityWeatherBinding

    @Inject
    lateinit var searchViewModelFactory: ViewModelFactory<SearchViewModel>
    private val searchViewModel by lazy {
        ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClickAction()
    }

    private fun onClickAction() {
        binding.ivClose.setOnClickListener {
            binding.edtSearchText.setText("")
        }
    }

    private fun init() {
        observeSearchTextChanges()
    }

    private fun observeSearchTextChanges() {
        binding.edtSearchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                searchViewModel.searchTextRX.onNext(p0.toString())
            }
        })
    }


}
