package com.example.core.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.presentation.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor() : BaseViewModel() {

    var lastQuery: String? = null


    private val searchTextLDPrivate by lazy { MutableLiveData<String>() }
    val searchTextLD: LiveData<String> get() = searchTextLDPrivate

    val searchTextRX by lazy {
        BehaviorSubject.create<String>()
    }

    private fun observePerformQuery() {
        searchTextRX.debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                search(it)
            }
            .addTo(compositeDisposable)
    }

    private fun search(query: String = "") {
        if (lastQuery == null || query != lastQuery) {
            lastQuery = query
            searchTextLDPrivate.value = query
        }
    }


    init {
        observePerformQuery()
    }

}

