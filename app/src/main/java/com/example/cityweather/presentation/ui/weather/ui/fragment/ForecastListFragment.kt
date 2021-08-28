package com.example.cityweather.presentation.ui.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cityweather.R
import com.example.cityweather.databinding.FragmentForcastListBinding
import com.example.cityweather.presentation.ui.base.BaseFragment
import com.example.cityweather.presentation.ui.base.ext.gone
import com.example.cityweather.presentation.ui.base.ext.showRetryDialog
import com.example.cityweather.presentation.ui.base.ext.visible
import com.example.cityweather.presentation.ui.weather.ui.adapter.TutorialsAdapter
import com.example.core.core.di.ViewModelFactory
import com.example.core.weather.presentation.uimodel.WeatherUiModel
import com.example.core.weather.presentation.viewmodel.SearchViewModel
import com.example.core.weather.presentation.viewmodel.WeatherViewModel
import com.example.core.weather.presentation.viewstate.WeatherViewState
import javax.inject.Inject

class ForecastListFragment : BaseFragment() {
    private lateinit var binding: FragmentForcastListBinding

    private val tutorialsAdapter = TutorialsAdapter()

    @Inject
    lateinit var tutorialsViewModelFactory: ViewModelFactory<WeatherViewModel>
    private val tutorialsViewModel by lazy {
        ViewModelProvider(this, tutorialsViewModelFactory).get(WeatherViewModel::class.java)
    }

    @Inject
    lateinit var searchViewModelFactory: ViewModelFactory<SearchViewModel>
    private val searchViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            searchViewModelFactory
        ).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForcastListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        observePreformSearch()
        initTutorialsRV()
        observeViewState()
    }

    private fun initTutorialsRV() {
        binding.rvTutorials.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTutorials.adapter = tutorialsAdapter
    }

    private fun observeViewState() {
        tutorialsViewModel.weatherViewStateLD.observe(this) {
            handleViewState(it)
        }

    }

    private fun observePreformSearch() {
        searchViewModel.searchTextLD.observe(this) {
            tutorialsViewModel.getForecast(it)
        }
    }

    private fun handleViewState(viewState: WeatherViewState) {
        when (viewState) {
            WeatherViewState.Loading -> { loadingState() }
            is WeatherViewState.onError -> errorState(viewState.error)
            is WeatherViewState.onSuccess -> successState(viewState.result)
            WeatherViewState.emptyState -> { showEmptyState() }
            WeatherViewState.isLoadFromCache -> { showResultsNotAccurate()}
        }
    }

    private fun showEmptyState() {
        binding.progressRootView.rootView.gone()
        binding.errMessageRootView.rootView.visible()
        binding.errMessageRootView.tvErrorMessage.text = getString(R.string.err_empty_result)
    }

    private fun showResultsNotAccurate() {

    }

    private fun successState(result: List<WeatherUiModel>) {
        binding.progressRootView.rootView.gone()
        binding.errMessageRootView.rootView.gone()
        setTutorials(result)
    }

    private fun setTutorials(result: List<WeatherUiModel>) {
        tutorialsAdapter.setData(result)
    }

    private fun errorState(error: Throwable) {
        showRetryDialog(true) {
            searchViewModel.lastQuery?.let { tutorialsViewModel.getForecast(it) }
        }
        binding.progressRootView.rootView.gone()
        binding.errMessageRootView.rootView.gone()
    }

    private fun loadingState() {
        tutorialsAdapter.clear()
        binding.progressRootView.rootView.visible()
        binding.errMessageRootView.rootView.gone()
    }

}