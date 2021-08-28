package com.example.cityweather.presentation.ui.base

import com.example.cityweather.presentation.ui.custom.LoadingProgressDialog
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    internal var mProgressDialog: LoadingProgressDialog? = null


    override fun onDestroy() {
        super.onDestroy()
        mProgressDialog?.dismiss()
        mProgressDialog = null
    }
}