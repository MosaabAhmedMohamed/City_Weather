package com.example.cityweather.presentation.ui.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cityweather.databinding.DialogLoadingBinding
import com.example.cityweather.presentation.ui.base.BaseDialog

class LoadingProgressDialog() : BaseDialog() {

    private lateinit var binding: DialogLoadingBinding
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

 
    
    companion object {


        fun newInstance(): LoadingProgressDialog {
            return LoadingProgressDialog()
        }
    }

}