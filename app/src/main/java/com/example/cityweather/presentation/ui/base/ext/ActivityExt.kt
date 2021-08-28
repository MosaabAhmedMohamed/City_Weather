package com.example.cityweather.presentation.ui.base.ext

import com.example.cityweather.presentation.ui.base.BaseActivity
import com.example.cityweather.presentation.ui.custom.LoadingProgressDialog
import com.example.cityweather.presentation.ui.custom.RetryDialog


fun BaseActivity.hideLoading() {
    if (mProgressDialog != null && mProgressDialog!!.isVisible) {
        mProgressDialog!!.dismiss()
    }
}

fun BaseActivity.showLoading() {
    hideLoading()
    mProgressDialog = LoadingProgressDialog.newInstance().apply {
        show(supportFragmentManager, LoadingProgressDialog::class.simpleName)
    }
}

fun BaseActivity.showRetryDialog(retry: Boolean, onRetry: () -> Unit) {
    if (retry) RetryDialog.newInstance(onRetry)
        .show(supportFragmentManager, RetryDialog::class.simpleName)
}

