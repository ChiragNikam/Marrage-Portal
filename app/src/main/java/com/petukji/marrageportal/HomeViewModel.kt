package com.petukji.marrageportal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel: ViewModel() {
    private val _showGirls = MutableStateFlow(false)
    val showGirls get() = _showGirls

    fun updateShowGirls(showGirls: Boolean){
        _showGirls.value = showGirls
    }
}