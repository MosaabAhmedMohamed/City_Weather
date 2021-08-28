package com.example.cityweather.presentation.ui.base.ext

import androidx.fragment.app.Fragment
import com.example.cityweather.presentation.ui.base.BaseActivity
import com.example.cityweather.presentation.ui.custom.RetryDialog

fun Fragment.showLoading(visibility: Boolean) {
    if (visibility) (requireActivity() as BaseActivity).showLoading()
    else (requireActivity() as BaseActivity).hideLoading()
}

fun Fragment.showRetryDialog(retry: Boolean, onRetry: () -> Unit) {
    if (retry) RetryDialog.newInstance(onRetry)
        .show(childFragmentManager, RetryDialog::class.simpleName)
}